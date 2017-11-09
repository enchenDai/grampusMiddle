package com.deepblue.middleware.web.rest;

import com.deepblue.middleware.service.LuisService;
import com.deepblue.middleware.web.rest.vm.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by enchen on 10/10/17.
 */
@RestController
@RequestMapping("/api/luis")
public class LuisResource {

    @Autowired
    LuisService luisService;

    @RequestMapping(value = "/text/analysis", method = RequestMethod.GET)
    public ResponseEntity<WebResult> personRegister(@RequestParam String targetText) {

        return ResponseEntity.ok(
                WebResult.ok(
                        luisService.textAnalysis(targetText)
                )
        );
    }


}
