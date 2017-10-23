package com.deepblue.middleware.service;

import com.alibaba.common.lang.StringUtil;
import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.service.dto.*;
import com.deepblue.middleware.service.util.ErrorCodeHandler;
import com.deepblue.middleware.service.util.RequestMessageXmlFormatter;
import com.deepblue.middleware.web.rest.errors.ExceptionTranslator;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by enchen on 10/12/17.
 */
@Service
public class GrampusClientService {

    @Value("${palm.base-url:}")
    private String baseUrl;

    /**
     * 个人掌脉操作 url
     */
    @Value("${palm.operation-url:}")
    private String operationUrl;

    /**
     * 新增掌脉集 url
     */
    @Value("${palm.palmset-insert-url:}")
    private String palmsetInsertUrl;

    /**
     * 更新掌脉集 url
     */
    @Value("${palm.palmset-update-url:}")
    private String palmsetUpdateUrl;

    /**
     * 查询掌脉集详情 url
     */
    @Value("${palm.palmset-detail-url:}")
    private String palmsetDetailUrl;

    /**
     * palmset 批量追加/删除 人员信息 url
     */
    @Value("${palm.palmset-person-url:}")
    private String palmsetPersonUrl;

    /**
     * 掌脉1:1 识别
     */
    @Value("${palm.palm-verify-url:}")
    private String palmVerifyUrl;

    /**
     * 掌脉1:N 认证
     */
    @Value("${palm.palm-identify-url:}")
    private String palmIdentifyUrl;

    /**
     * 用户 id 在掌脉系统中是否注册
     */
    @Value("${palm.person-valid-url:}")
    private String personValidUrl;

    /**
     * 查询所有 palmset 名称的集合
     */
    @Value("${palm.palmset-name-list-query-url:}")
    private String palmsetNameListQueryUrl;

    private RestTemplate restTemplate = new RestTemplate();

    private final Logger log = LoggerFactory.getLogger(GrampusClientService.class);

    /**
     * 个人掌脉操作（创建、更新、删除）
     *
     * @param registerPostMessage
     * @return
     */
    public String operationPalm(RegisterPostMessage registerPostMessage) {
        String xml = RequestMessageXmlFormatter.toC1001XML(registerPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + operationUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palm operation post exception : " + e.getMessage());
        }


        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 创建掌脉集
     *
     * @param palmsetPostMessage
     * @return
     */
    public String insertPalmset(PalmsetPostMessage palmsetPostMessage) {
        String xml = RequestMessageXmlFormatter.toC3002XML(palmsetPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmsetInsertUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palmset insert post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_SET_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 更新掌脉集
     *
     * @param palmsetPostMessage
     * @return
     */
    public String updatePalmset(PalmsetPostMessage palmsetPostMessage) {
        String xml = RequestMessageXmlFormatter.toC3002XML(palmsetPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmsetUpdateUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palmset update post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_SET_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 获取掌脉集信息
     *
     * @param palmsetPostMessage
     * @return
     */
    public String getPalmsetDetail(PalmsetPostMessage palmsetPostMessage) {
        String xml = RequestMessageXmlFormatter.toC3002XML(palmsetPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmsetDetailUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palmset detail post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_SET_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * palmset 批量追加/删除 人员信息
     *
     * @param palmsetNotifyPostMessage
     * @return
     */
    public String palmsetNotify(PalmsetNotifyPostMessage palmsetNotifyPostMessage) {
        String xml = RequestMessageXmlFormatter.toC3001XML(palmsetNotifyPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmsetPersonUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palmset person operation post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALMSET_PERSON_OPERATION_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 1:1 识别
     *
     * @param verifyPostMessage
     * @return
     */
    public String verifyPalm(VerifyPostMessage verifyPostMessage) {
        String xml = RequestMessageXmlFormatter.toC2001XML(verifyPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmVerifyUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palm verify post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_VERIFY_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 1:N  认证
     *
     * @param identifyPostMessage
     * @return
     */
    public String identifyPalm(IdentifyPostMessage identifyPostMessage) {
        String xml = RequestMessageXmlFormatter.toC2003XML(identifyPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmIdentifyUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palm identify post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_IDENTIFY_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 轮训调用 1:N 比对 （轮训单步）
     *
     * @param identifyPostMessage
     * @return
     */
    public String pollingIdentifyPalm(IdentifyPostMessage identifyPostMessage) {
        String xml = RequestMessageXmlFormatter.toC2003XML(identifyPostMessage);

        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmIdentifyUrl, requestEntity, String.class);
        } catch (Exception e) {
            log.error("polling identify palm error, palmset name: " + identifyPostMessage.getPalmsetName());
            return null;
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            return null;
        }
    }

    /**
     * 查询 userId 在掌脉系统中是否存在
     *
     * @param queryPostMessage
     * @return
     */
    public String personValidQuery(QueryPostMessage queryPostMessage) {
        String xml = RequestMessageXmlFormatter.toC4001XML(queryPostMessage);
        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + personValidUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("person valid post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.USER_ID_EXIST_VALID_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    /**
     * 查询所有 palmset 名称的集合
     *
     * @return
     */
    public String getlistPalmsetName() {
        QueryPostMessage queryPostMessage = new QueryPostMessage();
        String xml = RequestMessageXmlFormatter.toC4001XML(queryPostMessage);
        String result = null;
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(xml, requestHeaders);
        try {
            result = restTemplate.postForObject(baseUrl + palmsetNameListQueryUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palmset name list post exception : " + e.getMessage());
        }

        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.USER_ID_EXIST_VALID_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }

    private JSON convertXMLToJson(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xml);
        return json;
    }
}
