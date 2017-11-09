package com.deepblue.middleware.service.dto.luis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by enchen on 11/9/17.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextAnalysisRes implements Serializable {

    @JsonProperty("query")
    private String query;

    @JsonProperty("topScoringIntent")
    private TextAnalysisRes.Intent topScoringIntent;

    @JsonProperty("intents")
    private List<TextAnalysisRes.Intent> intents;

    @JsonProperty("entities")
    private List<TextAnalysisRes.LuisEntity> entities;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Intent implements Serializable {

        private String intent;
        private String score;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class LuisEntity implements Serializable {

        private String entity;

        private String type;

        private String startIndex;

        private String endIndex;

        private String score;
    }

}
