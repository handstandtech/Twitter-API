package com.handstandtech.twitter.shared.model;

import java.util.Date;

public class TweetLite {

	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 1L;

	private Double latitude;

	private Double longitude;

	private Date createdDate;

	private String text;

	public TweetLite() {

	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return createdDate + " | [" + latitude + ", " + longitude + "] | " + text;
	}

}
