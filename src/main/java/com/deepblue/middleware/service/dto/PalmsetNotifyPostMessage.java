package com.deepblue.middleware.service.dto;

import com.deepblue.middleware.service.enums.PalmsetNotifyEnum;

import java.util.List;

/**
 * PALMSET内N值的增加和删除
 */
public class PalmsetNotifyPostMessage extends RequestHeaderMessage {
	private String palmsetName;
	private PalmsetNotifyEnum notify;
	private List<String> userIdList;
	
	public String getPalmsetName() {
		return palmsetName;
	}

	public void setPalmsetName(String palmsetName) {
		this.palmsetName = palmsetName;
	}

	public PalmsetNotifyEnum getNotify() {
		return notify;
	}

	public void setNotify(PalmsetNotifyEnum notify) {
		this.notify = notify;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
}
