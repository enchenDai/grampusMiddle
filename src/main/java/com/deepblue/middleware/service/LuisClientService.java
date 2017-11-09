package com.deepblue.middleware.service;

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

//    @Value("${luis.text-analysis.key:}")
//    private String luisAnalysisKey;
//
//    @Value("${luis.text-analysis.suffixUrl:}")
//    private String luisAnalysissuffixUrl;

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
            //result = restTemplate.postForObject(luisAnalysisBaseUrl + luisAnalysisKey + luisAnalysissuffixUrl + targetText, TextAnalysisRes, String.class);
            result = restTemplate.getForEntity(luisAnalysisBaseUrl + targetText, String.class).getBody();
        } catch (Exception e) {
            throw new RuntimeException("text analysis exception : " + e.getMessage());
        }
        return result;
    }


}
