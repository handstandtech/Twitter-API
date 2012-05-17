//package com.handstandtech.twitter.server;
//
//import java.net.HttpURLConnection;
//
//import oauth.signpost.OAuthConsumer;
//import oauth.signpost.exception.OAuthCommunicationException;
//import oauth.signpost.exception.OAuthExpectationFailedException;
//import oauth.signpost.exception.OAuthMessageSignerException;
//
//import com.handstandtech.server.rest.Authenticator;
//import com.handstandtech.shared.model.rest.RequestAuthentication;
//
//public class OAuthAuthenticator implements Authenticator {
//
//	private OAuthConsumer consumer;
//
//	public OAuthAuthenticator(OAuthConsumer consumer) {
//		this.setConsumer(consumer);
//	}
//
//	public void setConsumer(OAuthConsumer consumer) {
//		this.consumer = consumer;
//	}
//
//	public OAuthConsumer getConsumer() {
//		return consumer;
//	}
//
//	@Override
//	public void authenticate(HttpURLConnection connection) {
//		if (consumer != null) {
//			try {
//				consumer.sign(connection);
//			} catch (OAuthMessageSignerException e) {
//				e.printStackTrace();
//			} catch (OAuthExpectationFailedException e) {
//				e.printStackTrace();
//			} catch (OAuthCommunicationException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//
//	@Override
//	public RequestAuthentication getRequestAuthenticationType() {
//		return RequestAuthentication.OAUTH;
//	}
//
//}
