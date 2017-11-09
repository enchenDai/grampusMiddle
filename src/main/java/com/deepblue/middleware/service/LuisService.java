package com.deepblue.middleware.service;

import com.alibaba.fastjson.JSON;
import com.deepblue.middleware.service.dto.luis.TextAnalysisRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by enchen on 11/9/17.
 */
@Service
public class LuisService {

    @Autowired
    LuisClientService luisClientService;

    public TextAnalysisRes textAnalysis(String targetText) {
        String result = luisClientService.textAnalysis(targetText);
        TextAnalysisRes textAnalysisRes = JSON.parseObject(result, TextAnalysisRes.class);
        return textAnalysisRes;
    }
}
