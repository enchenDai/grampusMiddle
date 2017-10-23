package com.deepblue.middleware.service.dto;

/**
 * Redis���غ�ĽY����ԃ
 */
public class RedisReloadResultPostMessage extends RequestHeaderMessage {
	private int resultId;

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
}
