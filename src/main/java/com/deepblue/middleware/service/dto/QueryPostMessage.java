package com.deepblue.middleware.service.dto;

/**
 * ��ѯ����
 */
public class QueryPostMessage extends RequestHeaderMessage {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
