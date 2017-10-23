package com.deepblue.middleware.service.util;

import java.util.Date;


import com.alibaba.common.lang.StringUtil;
import com.deepblue.middleware.service.dto.*;
import com.deepblue.middleware.service.util.lang.DateUtil;
import com.deepblue.middleware.service.util.lang.XmlFormatHelper;


public class RequestMessageXmlFormatter {
	private static final String SIGNATURE = "signature";
	private static final String TRAN_DATE = "tranDate";
	private static final String TRAN_TIME = "tranTime";

	/**
	 * 禁用构造函数
	 */
	private RequestMessageXmlFormatter() {
		// 禁用构造函数
	}

	/**
	 * 将RequestMessage对象转换为可逆的XML格式(采集注册)
	 *
	 * @param message RequestMessage对象
	 * @return
	 */
	public static String toC1001XML(RegisterPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}
		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode(SIGNATURE, message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("userId", message.getUserId()));
		if (message.getType() != null) {
			xml.append(RequestXmlFormatHelper.getSimpleNode("type", message.getType().getTypeCode()));
		}
		if (!StringUtil.isEmpty(message.getVein())) {
			xml.append(RequestXmlFormatHelper.getSimpleNode("vein", message.getVein()));
		}
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * 1:1识别
	 *
	 * @param message
	 * @return
	 */
	public static String toC2001XML(VerifyPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("userId", message.getUserId()));
		if (!StringUtil.isEmpty(message.getBizserialNo())) {
			xml.append(XmlFormatHelper.getSimpleNode("bizserialNo", message.getBizserialNo()));
		}
		xml.append(RequestXmlFormatHelper.getSimpleNode("capture", message.getCapture()));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * 1:1识别(没有数据库落地)
	 *
	 * @param message
	 * @return
	 */
	public static String toC2002XML(VerifyNoDBPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		if (!StringUtil.isEmpty(message.getBizserialNo())) {
			xml.append(XmlFormatHelper.getSimpleNode("bizserialNo", message.getBizserialNo()));
		}
		xml.append(RequestXmlFormatHelper.getSimpleNode("vein", message.getVein()));
		xml.append(RequestXmlFormatHelper.getSimpleNode("capture", message.getCapture()));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * 1:N认证
	 *
	 * @param message
	 * @return
	 */
	public static String toC2003XML(IdentifyPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("palmsetName", message.getPalmsetName()));
		if (!StringUtil.isEmpty(message.getBizserialNo())) {
			xml.append(XmlFormatHelper.getSimpleNode("bizserialNo", message.getBizserialNo()));
		}
		xml.append(RequestXmlFormatHelper.getSimpleNode("capture", message.getCapture()));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * PALMSET内N值得增加和删除
	 *
	 * @param message
	 * @return
	 */
	public static String toC3001XML(PalmsetNotifyPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("palmsetName", message.getPalmsetName()));
		if (message.getNotify() != null) {
			xml.append(RequestXmlFormatHelper.getSimpleNode("notify", message.getNotify().getTypeCode()));
		}
		xml.append(RequestXmlFormatHelper.getListNode("userIdList", message.getUserIdList()));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * PALMSET 新增、更新
	 *
	 * @param message
	 * @return
	 */
	public static String toC3002XML(PalmsetPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("palmsetName", message.getPalmsetName()));
		if(null != message.getCount()){
			xml.append(RequestXmlFormatHelper.getSimpleNode("count", message.getCount()));
		}
		if(null != message.getTag()){
			xml.append(RequestXmlFormatHelper.getSimpleNode("tag", message.getTag()));
		}
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}


	/**
	 * 查询服务
	 *
	 * @param message
	 * @return
	 */
	public static String toC4001XML(QueryPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("userId", message.getUserId()));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * redis 重载服务
	 *
	 * @param message
	 * @return
	 */
	public static String toC5001XML(RedisReloadPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		if (!StringUtil.isEmpty(message.getPalmsetName())) {
			xml.append(RequestXmlFormatHelper.getSimpleNode("palmsetName", message.getPalmsetName()));
		}

		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	/**
	 * redis 重载服务
	 *
	 * @param message
	 * @return
	 */
	public static String toC5002XML(RedisReloadResultPostMessage message) {
		if (null == message) {
			return StringUtil.EMPTY_STRING;
		}

		// xml头+xml内部
		StringBuilder xml = new StringBuilder(RequestXmlFormatHelper.XML_HEADER);
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.APPLICATION));
		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.HEADER));
		xml.append(RequestXmlFormatHelper.getSimpleNode("signature", message.getSignature()));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_DATE, DateUtil.format(new Date(), DateUtil.SHORT_FORMAT)));
		xml.append(RequestXmlFormatHelper.getSimpleNode(TRAN_TIME, DateUtil.format(new Date(), DateUtil.TIMESSS_FORMAT)));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.HEADER));

		xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getSimpleNode("resultId", String.valueOf(message.getResultId())));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.PALMREQ));
		xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.APPLICATION));
		return xml.toString();
	}

	//	/**
	//	 * 扩展信息的处理
	//	 *
	//	 * @param headerMap 扩展信息
	//	 * @return
	//	 */
	//	private static String toString(Map<String, String> headerMap) {
	//		StringBuilder xml = new StringBuilder();
	//		if (!headerMap.isEmpty()) {
	//			Iterator<String> it = headerMap.keySet().iterator();
	//			xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.EXTENSIONS));
	//			while (it.hasNext()) {
	//				String name = it.next();
	//				String value = headerMap.get(name);
	//				xml.append(RequestXmlFormatHelper.getNodeHead(RequestXmlFormatHelper.EXTENSION));
	//				xml.append(RequestXmlFormatHelper.getSimpleNode(name, value));
	//				xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.EXTENSION));
	//			}
	//			xml.append(RequestXmlFormatHelper.getNodeTail(RequestXmlFormatHelper.EXTENSIONS));
	//		}
	//
	//		return xml.toString();
	//	}
}
