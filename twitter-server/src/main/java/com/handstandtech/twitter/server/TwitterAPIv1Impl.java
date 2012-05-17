package com.handstandtech.twitter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handstandtech.restclient.server.RESTClient;
import com.handstandtech.restclient.server.RESTClientProvider;
import com.handstandtech.restclient.server.auth.Authenticator;
import com.handstandtech.restclient.server.auth.oauth.OAuthAuthenticatorProvider;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.restclient.shared.model.RequestMethod;
import com.handstandtech.restclient.shared.util.RESTURLUtil;
import com.handstandtech.twitter.shared.Tweet;
import com.handstandtech.twitter.shared.TwitterUser;

public abstract class TwitterAPIv1Impl implements TwitterAPIv1 {

	protected static Logger log = LoggerFactory.getLogger(TwitterAPIv1Impl.class.getName());

	private static final String FORMAT = "json";

	private static final String V1_BASE_URL = "http://api.twitter.com/1";

	private static final String TWITTER_RATE_LIMIT_HEADER_KEY = "x-ratelimit-limit";
	private static final String TWITTER_RATE_LIMIT_REMAINING_HEADER_KEY = "x-ratelimit-remaining";
	private static final String TWITTER_RATE_LIMIT_RESET_HEADER_KEY = "x-ratelimit-reset";

	protected OAuthConsumer consumer;

	private RESTClientProvider clientProvider;

	private OAuthAuthenticatorProvider oAuthProvider;

	public TwitterAPIv1Impl(OAuthConsumer consumer, RESTClientProvider restProvider, OAuthAuthenticatorProvider oAuthProvider) {
		this.consumer = consumer;
		this.clientProvider = restProvider;
		this.oAuthProvider = oAuthProvider;
	}

	public TwitterAPIv1Impl(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		this.consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
		this.consumer.setTokenWithSecret(token, tokenSecret);
	}

	private List<Long> getFollowingIdsForScreenName(String screenName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("screen_name", screenName);
		return getFollowingIds(params);

	}
	

	

	private List<Long> getFollowingIds(Map<String, String> params) {
		RESTClient client = getRestClientImpl();
		String url = RESTURLUtil.createFullUrl(V1_BASE_URL + "/friends/ids."
				+ FORMAT, params);

		log.trace("Friend Url: " + url);

		RESTResult result = client.request(RequestMethod.GET, url, 				getAuthenticator());

		List<Long> currList = new ArrayList<Long>();
		if (result.getResponseCode() == 200) {
			try {
				JSONArray userIds = new JSONArray(result.getResponseBody());
				for (int i = 0; i < userIds.length(); i++) {
					currList.add(new Long(userIds.getInt(i)));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			log.error("Unable to get friends.");
		}
		log.debug("Returning List of Friend Ids: " + currList);
		return currList;
	}

	private List<TwitterUser> getTwitterFriends(List<Long> ids) {

		List<TwitterUser> twitterFriends = new ArrayList<TwitterUser>();
		List<Long> currList = new ArrayList<Long>();

		for (int i = 0; i < ids.size(); i++) {
			currList.add(ids.get(i));
			if (currList.size() == 100) {
				twitterFriends.addAll(getTwitterFriendsHelper(currList));
				currList.clear();
			}
		}

		if (currList.size() > 0) {
			twitterFriends.addAll(getTwitterFriendsHelper(currList));
		}

		return twitterFriends;
	}

	/**
	 * Calls and gets a list of twitter friends as long as there are less than
	 * 100
	 * 
	 * @param idListStr
	 * @return
	 */
	private List<TwitterUser> getTwitterFriendsHelper(List<Long> ids) {
		log.debug("Getting batch of " + ids.size() + " twitter users");

		List<TwitterUser> twitterUsers = new ArrayList<TwitterUser>();

		RESTClient client = getRestClientImpl();
		Map<String, String> params2 = new HashMap<String, String>();

		String idListStr = TwitterUtils.createIdListString(ids);
		params2.put("user_id", idListStr);
		String friendsInfoUrl = RESTURLUtil.createFullUrl("http://api.twitter.com/1/users/lookup." + FORMAT, params2);
		log.debug("Calling URL: " + friendsInfoUrl);
		RESTResult friendInfoResult = client.request(RequestMethod.GET, friendsInfoUrl, getAuthenticator());
		// log.trace(friendInfoResult.toString());

		try {
			JSONArray twitterFriendsJsonArray = new JSONArray(friendInfoResult.getResponseBody());
			for (int x = 0; x < twitterFriendsJsonArray.length(); x++) {
				JSONObject twitterFriendJson = twitterFriendsJsonArray.getJSONObject(x);
				TwitterUser user = TwitterUtils.getUserFromResponse(twitterFriendJson.toString());
				twitterUsers.add(user);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			Map<String, String> headers = friendInfoResult.getResponseHeaders();
			log.debug(headers.get(TWITTER_RATE_LIMIT_REMAINING_HEADER_KEY) + " of " + headers.get(TWITTER_RATE_LIMIT_HEADER_KEY) + " remaining.  Resets at "
					+ headers.get(TWITTER_RATE_LIMIT_RESET_HEADER_KEY));
		} catch (Exception e) {
			log.error("Had a Problem reading the response headers!");
		}

		log.debug("Returning a total of " + twitterUsers.size() + " Twitter Users Found!");

		return twitterUsers;

	}
	
	private Authenticator getAuthenticator() {
		Authenticator a = null;
		if (consumer != null) {
			a = oAuthProvider.getAuthenticator(consumer);
		}
		return a;
	}

	@Override
	public List<TwitterUser> getTwitterFriendsForScreenName(String screenName) {
		log.debug("Getting Twitter Friends for Screen Name: @" + screenName);
		List<Long> ids = getFollowingIdsForScreenName(screenName);
		log.debug("Found " + ids.size() + " friends, going to get their info.");
		List<TwitterUser> friends = getTwitterFriends(ids);
		return friends;
	}

	private RESTClient getRestClientImpl() {
		return clientProvider.getNewClientInstance();
	}

	@Override
	public TwitterUser getCurrentUserInfo() {
		String url = V1_BASE_URL + "/account/verify_credentials." + FORMAT;

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url, getAuthenticator());

		return TwitterUtils.getUserFromResponse(result.getResponseBody());

	}

	@Override
	public List<TwitterUser> getTwitterFriendsForId(String id) {
		log.debug("Getting Twitter Friends for ID: " + id);
		List<Long> ids = getFollowingIdsForId(id);
		log.debug("Found " + ids.size() + " friends, going to get their info.");
		List<TwitterUser> friends = getTwitterFriends(ids);
		return friends;
	}

	private List<Long> getFollowingIdsForId(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		return getFollowingIds(params);
	}

	@Override
	public List<Tweet> getTimelineForUserId(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_id", id);
		params.put("count", "100");
		params.put("include_rts", "true");
		String url = RESTURLUtil.createFullUrl(V1_BASE_URL + "/statuses/user_timeline." + FORMAT, params);
		log.debug("Timeline Url: " + url);

		RESTClient client = getRestClientImpl();
		RESTResult result = client.request(RequestMethod.GET, url, getAuthenticator());
		return TwitterUtils.getTweetsFromTimeline(result);
	}

}
