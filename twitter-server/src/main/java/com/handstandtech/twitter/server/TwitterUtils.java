package com.handstandtech.twitter.server;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.handstandtech.restclient.shared.model.RESTResult;
import com.handstandtech.twitter.shared.Tweet;
import com.handstandtech.twitter.shared.TwitterUser;

public class TwitterUtils {
	protected static final String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";

	public static List<Tweet> getTweetsFromTimeline(RESTResult result) {
		List<Tweet> tweets = new ArrayList<Tweet>();
		try {
			JSONArray tweetsArray = new JSONArray(result.getResponseBody());

			for (int i = 0; i < tweetsArray.length(); i++) {
				JSONObject tweetObj = tweetsArray.getJSONObject(i);
				Gson gson = getTwitterGson();
				Tweet tweet = gson.fromJson(tweetObj.toString(), Tweet.class);
				tweets.add(tweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tweets;
	}

	private static Gson getTwitterGson() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
				new JsonDeserializer<Date>() {
					public Date deserialize(JsonElement arg0, Type arg1,
							JsonDeserializationContext arg2)
							throws JsonParseException {
						String date = arg0.getAsString();
						SimpleDateFormat df = new SimpleDateFormat(
								TWITTER_DATE_FORMAT);

						try {
							return df.parse(date);
						} catch (ParseException e) {
							throw new JsonParseException("Cannot parse date: "
									+ e.getMessage(), e);
						}
					}

				}).create();
		return gson;
	}

	public static TwitterUser getUserFromResponse(String jsonString) {
		Gson gson = getTwitterGson();
		return gson.fromJson(jsonString, TwitterUser.class);
	}

	public static String createIdListString(List<Long> currList) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < currList.size(); i++) {
			sb.append(currList.get(i).longValue() + "");
			if (i < (currList.size() - 1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
