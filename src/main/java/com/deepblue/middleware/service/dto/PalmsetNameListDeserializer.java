package com.deepblue.middleware.service.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by enchen on 10/19/17.
 */
public class PalmsetNameListDeserializer extends JsonDeserializer<List<String>> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken t = jsonParser.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jsonParser.getText().trim();
            String nameListStr = str.substring(1, str.length() - 1);
            List<String> nameListArr = Arrays.asList(nameListStr.split(","));
            return nameListArr.stream().map(String::trim).collect(Collectors.toList());
        }
        throw deserializationContext.mappingException("palmset name deserialize error");

    }
}
