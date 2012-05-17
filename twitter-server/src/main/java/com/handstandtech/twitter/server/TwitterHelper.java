//package com.handstandtech.twitter.server;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import oauth.signpost.OAuthConsumer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.handstandtech.restclient.server.RESTClient;
//import com.handstandtech.restclient.server.auth.OAuthAuthenticator;
//import com.handstandtech.restclient.server.impl.RESTClientJavaNetImpl;
//import com.handstandtech.restclient.shared.model.RESTResult;
//import com.handstandtech.restclient.shared.model.RequestMethod;
//import com.handstandtech.restclient.shared.util.RESTURLUtil;
//
//public class TwitterHelper {
//
//	private static final String V1_BASE_URL = "http://api.twitter.com/1";
//
//	private static Logger log = LoggerFactory.getLogger(TwitterHelper.class
//			.getName());
//
//	private OAuthConsumer consumer;
//
//	public TwitterHelper() {
//	}
//
//	public TwitterHelper(OAuthConsumer consumer) {
//		this.consumer = consumer;
//	}
//
//	public void updateStatus(String status) {
//		String updateStatusUrl = V1_BASE_URL + "/statuses/update.json";
//		Map<String, String> params = new HashMap<String, String>();
//		try {
//			params.put("status", URLEncoder.encode(status, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		String url = RESTURLUtil.createFullUrl(updateStatusUrl, params);
//
//		RESTClientJavaNetImpl client = new RESTClientJavaNetImpl();
//		RESTResult result = client.request(RequestMethod.POST, url,
//				new OAuthAuthenticator(consumer));
//
//		log.info(result.toString());
//	}
//
//	public RESTResult doSearch(String query) {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("q", query);
//
//		String url = RESTURLUtil.createFullUrl(
//				"http://search.twitter.com/search.json", params);
//		RESTClient client = new RESTClientJavaNetImpl();
//		RESTResult result = client.request(RequestMethod.GET, url,
//				new OAuthAuthenticator(consumer));
//		System.out.println("Search Response: " + result);
//		return result;
//	}
//
//	public RESTResult getCurrentUser() {
//		String baseUrl = V1_BASE_URL + "/account/verify_credentials.json";
//		RESTClientJavaNetImpl client = new RESTClientJavaNetImpl();
//		RESTResult result = client.request(RequestMethod.GET, baseUrl,
//				new OAuthAuthenticator(consumer));
//
//		log.info(result.toString());
//		return result;
//	}
//
//	public void printConsumerInfo() {
//		log.info("Token: " + consumer.getToken());
//		log.info("Token Secret: " + consumer.getTokenSecret());
//		log.info("Consumer Key: " + consumer.getConsumerKey());
//		log.info("Consumer Secret: " + consumer.getConsumerSecret());
//	}
//}
