package com.deepblue.middleware.service.dto;

/**
 * 1:1ʶ��
 */
public class VerifyPostMessage extends RequestHeaderMessage {
	private String userId;
	private String bizserialNo;
	private String capture;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBizserialNo() {
		return bizserialNo;
	}

	public void setBizserialNo(String bizserialNo) {
		this.bizserialNo = bizserialNo;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}
}
