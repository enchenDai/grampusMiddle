package com.deepblue.middleware.service.dto;

/**
 * 1:1ʶ��(û�����ݿ����)
 */
public class VerifyNoDBPostMessage extends RequestHeaderMessage {
	private String bizserialNo;
	private String vein;
	private String capture;

	public String getBizserialNo() {
		return bizserialNo;
	}

	public void setBizserialNo(String bizserialNo) {
		this.bizserialNo = bizserialNo;
	}

	public String getVein() {
		return vein;
	}

	public void setVein(String vein) {
		this.vein = vein;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}
}
