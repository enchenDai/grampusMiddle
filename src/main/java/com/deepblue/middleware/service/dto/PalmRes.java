package com.deepblue.middleware.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by enchen on 10/10/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Setter
@Getter
public class PalmRes {


    /**
     * 交易码
     */
    private String transCode;

    /**
     * 掌脉集描述
     */
    private String tag;

    /**
     * palmset 名称
     */
    private String palmsetName;

    /**
     * 最大容量
     */
    private String count;

    /**
     * 掌脉集 n 值
     */
    private String n;

    /**
     * 交易返回码
     */
    private String code;

    /**
     * 交易返回码说明
     */
    private String message;
}
