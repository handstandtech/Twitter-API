package com.handstandtech.twitter.shared;

import java.io.Serializable;
import java.util.Date;

public class SearchResult implements Serializable {

	/**
	 * Default Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	private String from_user;
	private Long from_user_id;
	private Long id;
	private String iso_language_code;
	private String text;
	private String to_user;
	private Long to_user_id;
	private Date created_at;

	public String getFromUser() {
		return from_user;
	}

	public void setFromUser(String fromUser) {
		from_user = fromUser;
	}

	public Long getFromUserId() {
		return from_user_id;
	}

	public void setFromUserId(Long fromUserId) {
		from_user_id = fromUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsoLanguageCode() {
		return iso_language_code;
	}

	public void setIsoLanguageCode(String isoLanguageCode) {
		iso_language_code = isoLanguageCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getToUser() {
		return to_user;
	}

	public void setToUser(String toUser) {
		to_user = toUser;
	}

	public Long getToUserId() {
		return to_user_id;
	}

	public void setToUserId(Long toUserId) {
		to_user_id = toUserId;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date createdAt) {
		created_at = createdAt;
	}

}
