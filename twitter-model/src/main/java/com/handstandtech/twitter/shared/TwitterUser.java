package com.handstandtech.twitter.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

public class TwitterUser implements Serializable {

	/**
	 * @version 1 - 2010.3.01 - Inititial Impl
	 */
	private static final long serialVersionUID = 20100301L;

	@Id
	private String id;

	private Boolean geo_enabled;

	private Date created_at;

	private String description;

	private String lang;

	private String location;

	private String name;

	private String screen_name;

	private Long utc_offset;

	private Long statuses_count;
	private Long followers_count;

	private Long friends_count;

	private String profile_image_url;

	public String getId() {
		return id;
	}

	public Boolean getGeo_enabled() {
		return geo_enabled;
	}

	public void setGeo_enabled(Boolean geoEnabled) {
		geo_enabled = geoEnabled;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date createdAt) {
		created_at = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screenName) {
		screen_name = screenName;
	}

	public Long getUtc_offset() {
		return utc_offset;
	}

	public void setUtc_offset(Long utcOffset) {
		utc_offset = utcOffset;
	}

	public Long getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(Long statusesCount) {
		statuses_count = statusesCount;
	}

	public Long getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(Long followersCount) {
		followers_count = followersCount;
	}

	public Long getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(Long friendsCount) {
		friends_count = friendsCount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
