package com.petcemetery.petcemetery.integracao.weather;

import com.petcemetery.petcemetery.integracao.weather.dto.ClimaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

     private final WeatherService weatherService;

     @GetMapping("/previsao")
     public ResponseEntity<ClimaDTO> getClima(@RequestParam LocalDate data) {
         return ResponseEntity.ok(weatherService.getClima(data));
     }
}
