package com.deepblue.middleware.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by enchen on 10/12/17.
 */
@Getter
@Setter
public class VerifyPalmDTO {

    private String userId;

    private String capture;

    private String bizserialNo;

    private String deviceNo;
}
