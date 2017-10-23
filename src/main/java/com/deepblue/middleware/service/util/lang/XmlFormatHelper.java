package com.deepblue.middleware.service.util.lang;

/**
 * XML ��ʽ��������
 * 
 * @author wang shuai
 */
public final class XmlFormatHelper {
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

	public static final String PALMRES = "PalmRes";

	public static final String EXTENSIONS = "extensions";

	public static final String EXTENSION = "extension";

	/**
	 * ���ù��캯��
	 */
	private XmlFormatHelper() {
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
}
