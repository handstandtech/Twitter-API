package com.handstandtech.twitter.server;

import java.util.List;

import com.handstandtech.twitter.shared.Tweet;
import com.handstandtech.twitter.shared.TwitterUser;

public interface TwitterAPIv1 {

	public TwitterUser getCurrentUserInfo();

	public List<TwitterUser> getTwitterFriendsForScreenName(String screenName);

	public List<TwitterUser> getTwitterFriendsForId(String id);

	public List<Tweet> getTimelineForUserId(String id);
}
