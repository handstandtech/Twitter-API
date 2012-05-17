package com.handstandtech.twitter.shared;

import java.util.Date;

import javax.persistence.Id;

public class Tweet {

	/**
	 * @version 1 - 2010.3.01 - Inititial Impl
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Long retweet_count;

	private Boolean retweeted;

	private String in_reply_to_status_id;
	private String in_reply_to_user_id;
	private String in_reply_to_screen_name;

	private boolean favorited;

	private Date created_at;

	private String source;

	private boolean truncated;

	private String text;
	
	public Tweet() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getRetweet_count() {
		return retweet_count;
	}

	public void setRetweet_count(Long retweet_count) {
		this.retweet_count = retweet_count;
	}

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return created_at;
	}

	public void setRetweeted(Boolean retweeted) {
		this.retweeted = retweeted;
	}

	public Boolean getRetweeted() {
		return retweeted;
	}

	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}

	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}

	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}

	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}

	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}

	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}
}
