package com.petcemetery.petcemetery.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petcemetery.petcemetery.dto.ClimaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Value("${weather.key}")
    private String WEATHER_API_KEY;
    private final String CIDADE = "Rio de Janeiro";
    private final int Q = 1;

    public ClimaDTO getClima(LocalDate data) {

        RestClient restClient = RestClient.create();

        String json = restClient.get()
                .uri(String.format("https://api.weatherapi.com/v1/forecast.json?q=%s&days=%d&dt=%s&key=%s", CIDADE, Q, data, WEATHER_API_KEY))
                .retrieve()
                .body(String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readValue(json, JsonNode.class);
            JsonNode inicial = node.get("forecast").get("forecastday").get(0).get("day");
            System.out.println(inicial.asText());

            String tempMaxima = inicial.get("maxtemp_c").asText();
            String tempMinima = inicial.get("mintemp_c").asText();
            String icone = inicial.get("condition").get("icon").asText();

            return ClimaDTO.builder()
                    .temperaturaMaxima(tempMaxima)
                    .temperaturaMinima(tempMinima)
                    .icone(icone)
                    .build();
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
