package com.deepblue.middleware.service.util;

import java.util.List;

public class RequestXmlFormatHelper {
	/**
	 * XML ͷ
	 */
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	/**
	 * XML ���ǩ
	 */
	private static final String LEFT_LABEL = "<";

	/**
	 * XML �������
	 */
	private static final String TAG_LEFT_END = "</";

	/**
	 * XML �ұ�ǩ
	 */
	private static final String RIGHT_LABEL = ">";

	public static final String APPLICATION = "Application";

	public static final String HEADER = "Header";

	public static final String PALMREQ = "PalmReq";

	public static final String USERID = "userId";

	/**
	 * ���ù��캯��
	 */
	private RequestXmlFormatHelper() {
		// ���ù��캯��
	}

	/**
	 * ����xml�ڵ�ͷ��
	 * 
	 * @param name
	 * @return
	 */
	public static String getNodeHead(String name) {
		StringBuilder result = new StringBuilder();
		result.append(LEFT_LABEL).append(name).append(RIGHT_LABEL);
		return result.toString();
	}

	/**
	 * ����xml�ڵ�β��
	 * 
	 * @param name
	 * @return
	 */
	public static String getNodeTail(String name) {
		StringBuilder result = new StringBuilder();
		result.append(TAG_LEFT_END).append(name).append(RIGHT_LABEL);
		return result.toString();
	}

	/**
	 * ���ɼ�xml�ڵ��ı�
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static String getSimpleNode(String name, String value) {
		StringBuilder result = new StringBuilder();
		result.append(getNodeHead(name));
		result.append(value);
		result.append(getNodeTail(name));
		return result.toString();
	}
	
	/**
	 * ����List��ʽxml�ڵ��ı�
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static String getListNode(String name, List<String> value) {
		StringBuilder result = new StringBuilder();
		result.append(getNodeHead(name));
		for(String v:value){
			result.append(getNodeHead(USERID));
			result.append(v);
			result.append(getNodeTail(USERID));
		}
		result.append(getNodeTail(name));
		return result.toString();
	}
}
