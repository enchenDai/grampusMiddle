package com.deepblue.middleware.service.util;

import com.deepblue.middleware.service.dto.*;
import org.sword.lang.HttpUtils;

/**
 * request to domain.cn<br />
 * {@code new HttpRequests().request("url", PostMessage)}<br />
 * 
 * @author wang shuai
 */
public class HttpRequests {

	public HttpRequests() {
	}

	/**
	 *
	 * @param url
	 * @param xml
	 * @return a result object(JSONObject)
	 */
	private String request(String url, String xml) {
		String jsonStr = HttpUtils.post(url, xml);
		return jsonStr;
	}

	// all api here
	// 采集注册
	public String register(String url, RegisterPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC1001XML(message);
		return request(url, xml);
	}

	// 1:1识别
	public String verify(String url, VerifyPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC2001XML(message);
		return request(url, xml);
	}

	// 1:1识别(没有数据库落地)
	public String verifyNoDB(String url, VerifyNoDBPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC2002XML(message);
		return request(url, xml);
	}

	// 1:N认证
	public String identify(String url, IdentifyPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC2003XML(message);
		return request(url, xml);
	}

	// PALMSET内N值得增加和删除
	public String palmsetNotify(String url, PalmsetNotifyPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC3001XML(message);
		return request(url, xml);
	}

	// 查询服务
	public String query(String url, QueryPostMessage message) {
		String xml = RequestMessageXmlFormatter.toC4001XML(message);
		return request(url, xml);
	}

	//Redis重载服务
	public String redisReload(String url, RedisReloadPostMessage message){
		String xml = RequestMessageXmlFormatter.toC5001XML(message);
		return request(url, xml);
	}

	//Redis重载服务结果查询
	public String redisReloadResult(String url, RedisReloadResultPostMessage message){
		String xml = RequestMessageXmlFormatter.toC5002XML(message);
		return request(url, xml);
	}
}


