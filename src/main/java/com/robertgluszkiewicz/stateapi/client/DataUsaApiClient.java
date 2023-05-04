package com.robertgluszkiewicz.stateapi.client;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.robertgluszkiewicz.stateapi.exception.DataUsaApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertgluszkiewicz.stateapi.domain.DataUsaStateDto;

@Component
public class DataUsaApiClient {
    private static final String DATA_USA_API_ENDPOINT = "https://datausa.io/api/data";
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
        URI uri = UriComponentsBuilder.fromHttpUrl(DATA_USA_API_ENDPOINT)
                .queryParam("drilldowns", DRILLDOWNS)
                .queryParam("measures", MEASURES)
                .queryParam("year", YEAR)
                .build()
                .encode()
                .toUri();
        String response = restTemplate.getForEntity(uri, String.class).getBody();
        return Optional.ofNullable(response).orElseThrow(
                () -> new DataUsaApiException());
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
            throw new DataUsaApiException();
        }
    }
}
