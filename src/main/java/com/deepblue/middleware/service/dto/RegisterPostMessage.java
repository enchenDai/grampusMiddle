package com.deepblue.middleware.service.dto;


import com.deepblue.middleware.service.enums.PalmTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 采集注册报文信息
 * server 调用掌脉api 的 dto
 */
@Setter
@Getter
public class RegisterPostMessage extends RequestHeaderMessage{

	private String userId;

	private PalmTypeEnum type;

	private String vein;
}
