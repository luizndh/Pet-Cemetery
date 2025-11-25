package com.petcemetery.petcemetery.integracao.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petcemetery.petcemetery.integracao.weather.dto.ClimaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Slf4j
@Service
public class WeatherService {

    @Value("${weather.key}")
    private String WEATHER_API_KEY;
    private final String CIDADE = "Rio de Janeiro";
    private final int Q = 1;

    public ClimaDTO getClima(LocalDate data) {

        RestClient restClient = RestClient.create();

        String json = restClient.get()
                .uri(String.format("https://api.weatherapi.com/v1/forecast.json?q=%s&days=%d&dt=%s&key=%s", CIDADE, Q,
                        data, WEATHER_API_KEY))
                .retrieve()
                .body(String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readValue(json, JsonNode.class);
            JsonNode inicial = node.get("forecast").get("forecastday").get(0).get("day");
            log.debug("Dados do clima obtidos para data: {}", data);

            String tempMaxima = inicial.get("maxtemp_c").asText();
            String tempMinima = inicial.get("mintemp_c").asText();
            String icone = inicial.get("condition").get("icon").asText();

            return ClimaDTO.builder()
                    .temperaturaMaxima(tempMaxima)
                    .temperaturaMinima(tempMinima)
                    .icone(icone)
                    .build();
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar resposta da API de clima", e);
            throw new RuntimeException("Erro ao buscar dados do clima", e);
        }
    }
}
