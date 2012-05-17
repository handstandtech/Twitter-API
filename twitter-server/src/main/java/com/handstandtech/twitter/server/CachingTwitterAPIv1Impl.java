package com.handstandtech.twitter.server;

import java.util.List;

import oauth.signpost.OAuthConsumer;

import com.handstandtech.memcache.MemcacheScopeManager;
import com.handstandtech.restclient.server.RESTClientProvider;
import com.handstandtech.restclient.server.auth.oauth.OAuthAuthenticatorProvider;
import com.handstandtech.twitter.shared.TwitterUser;

public class CachingTwitterAPIv1Impl extends TwitterAPIv1Impl {
	private static final String FRIENDS = "friends";

	public CachingTwitterAPIv1Impl(OAuthConsumer consumer, RESTClientProvider restProvider, OAuthAuthenticatorProvider oAuthProvider) {
		super(consumer, restProvider, oAuthProvider);
	}

	@Override
	public List<TwitterUser> getTwitterFriendsForScreenName(String screenName) {
		String scope = getScope(TwitterUser.class, screenName);
		String key = FRIENDS;
		List<TwitterUser> items = (List<TwitterUser>) MemcacheScopeManager.getFromCache(scope, key);
		if (items == null || items.size() == 0) {
			items = super.getTwitterFriendsForScreenName(screenName);
			MemcacheScopeManager.setClean(scope, key, items);
		}
		return items;
	}

	@Override
	public List<TwitterUser> getTwitterFriendsForId(String id) {
		String scope = getScope(TwitterUser.class, id);
		String key = FRIENDS;
		List<TwitterUser> items = (List<TwitterUser>) MemcacheScopeManager.getFromCache(scope, key);
		if (items == null || items.size() == 0) {
			items = super.getTwitterFriendsForId(id);
			MemcacheScopeManager.setClean(scope, key, items);
		}
		return items;
	}

	public void clearTwitterFriendsForScreenName(String screenName) {
		String scope = getScope(TwitterUser.class, screenName);
		MemcacheScopeManager.setDirty(scope);
	}

	private String getScope(Class<?> clazz, String screenName) {
		return clazz.getSimpleName() + "-" + screenName + "-" + consumer.getToken() + "-" + consumer.getTokenSecret();
	}
}
