package com.deepblue.middleware.service;

import com.alibaba.common.lang.StringUtil;
import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.service.util.ErrorCodeHandler;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by enchen on 10/12/17.
 */
@Service
public class LuisClientService {

    @Value("${luis.text-analysis.baseUrl:}")
    private String luisAnalysisBaseUrl;

    @Value("${luis.text-analysis.key:}")
    private String luisAnalysisKey;

    @Value("${luis.text-analysis.suffixUrl:}")
    private String luisAnalysissuffixUrl;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * textAnalysis
     *
     * @param targetText
     * @return
     */
    public String textAnalysis(String targetText) {
        String result = null;
        try {
            result = restTemplate.postForObject(luisAnalysisBaseUrl + luisAnalysisKey + luisAnalysissuffixUrl + targetText, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("palm operation post exception : " + e.getMessage());
        }


        if (StringUtil.contains(result, "Application")) {
            return convertXMLToJson(result).toString();
        } else {
            throw new SystemException(ResultCode.PALM_ERROR, ErrorCodeHandler.errorCodeConvert(result));
        }
    }


}
