package com.deepblue.middleware.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by enchen on 10/13/17.
 */
@Getter
@Setter
public class PalmsetNotifyDTO {

    private String palmsetName;

    /**
     * "1"  增加Person
     * "2"  删除Person
     */
    private String notify;

    private List<String> userIdList;
}
