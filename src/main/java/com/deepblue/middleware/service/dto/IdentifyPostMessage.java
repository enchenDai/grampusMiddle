package com.deepblue.middleware.service.dto;

/**
 * 1:N��֤
 */
public class IdentifyPostMessage extends RequestHeaderMessage {
	private String palmsetName;
	private String bizserialNo;
	private String capture;

	public String getPalmsetName() {
		return palmsetName;
	}

	public void setPalmsetName(String palmsetName) {
		this.palmsetName = palmsetName;
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
