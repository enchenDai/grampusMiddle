package com.deepblue.middleware.service.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by enchen on 10/12/17.
 */
@Setter
@Getter
public class PalmsetPostMessage extends RequestHeaderMessage {

    private String palmsetName;

    private String tag;

    private String count;
}
