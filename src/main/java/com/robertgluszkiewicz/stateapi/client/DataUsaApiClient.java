package com.robertgluszkiewicz.stateapi.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertgluszkiewicz.stateapi.domain.DataUsaStateDto;
import com.robertgluszkiewicz.stateapi.exception.DataUsaApiResponseException;

@Component
public class DataUsaApiClient {
    private static final String DATAUSAAPIENDPOINT = "https://datausa.io/api/data";
    private static final String DRILLDOWNS = "State";
    private static final String MEASURES = "Population";
    private static final String YEAR = "latest";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public DataUsaApiClient(
            RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getResponse() {
        URI uri = UriComponentsBuilder.fromHttpUrl(DATAUSAAPIENDPOINT)
                .queryParam("drilldowns", DRILLDOWNS)
                .queryParam("measures", MEASURES)
                .queryParam("year", YEAR)
                .build()
                .encode()
                .toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return Optional.ofNullable(responseEntity.getBody()).orElseThrow(
                () -> new DataUsaApiResponseException("Data Usa Api error"));
    }

    public List<DataUsaStateDto> getStates() {
        try {
            JsonNode nodeResponse = objectMapper.readTree(getResponse()).get("data");
            List<DataUsaStateDto> dataUsaStateDtoList = new ArrayList<>();
            if (nodeResponse != null && nodeResponse.isArray()) {
                for (JsonNode node : nodeResponse) {
                    DataUsaStateDto dataUsaStateDto = new DataUsaStateDto(
                            node.get("ID State").asText(),
                            node.get("State").asText(),
                            node.get("Year").asText(),
                            node.get("Population").asInt()
                    );
                    dataUsaStateDtoList.add(dataUsaStateDto);
                }
            }
            return dataUsaStateDtoList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
