package com.deepblue.middleware.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enchen on 10/19/17.
 * @author enchen
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalmsetNameListDTO implements Serializable {

    @JsonProperty("PalmRes")
    private PalmRes palmRes;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class PalmRes implements Serializable {

        @JsonDeserialize(using = PalmsetNameListDeserializer.class)
        private List<String> palmsetNameList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
