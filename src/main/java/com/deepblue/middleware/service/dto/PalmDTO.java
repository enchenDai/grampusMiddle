package com.deepblue.middleware.service.dto;

/**
 * Created by enchen on 10/10/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * client 请求 server 接受的 dto
 */
@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalmDTO {

    /**
     * 用户唯一 id
     */
    @NotNull
    private String userId;

    /**
     * 掌脉信息
     */
    private String vein;

    /**
     * 操作类型
     * "1" : 新建
     * "2" : 更新
     * "3" : 删除
     */
    @NotNull
    private String type;

    private String deviceNo;
}
