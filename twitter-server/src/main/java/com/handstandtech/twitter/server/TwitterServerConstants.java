package com.handstandtech.twitter.server;

import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;

public class TwitterServerConstants {

	public static OAuthProvider getTwitterOAuthProvider() {
		String baseUrl = "http://twitter.com/oauth/";
		OAuthProvider provider = new DefaultOAuthProvider(baseUrl + "request_token", baseUrl + "access_token", baseUrl + "authorize");
		return provider;
	}
}
