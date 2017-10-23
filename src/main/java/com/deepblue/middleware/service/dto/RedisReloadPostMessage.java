package com.deepblue.middleware.service.dto;

/**
 * Redis����
 */
public class RedisReloadPostMessage extends RequestHeaderMessage {
	private String palmsetName;
	
	public String getPalmsetName() {
		return palmsetName;
	}

	public void setPalmsetName(String palmsetName) {
		this.palmsetName = palmsetName;
	}
}
