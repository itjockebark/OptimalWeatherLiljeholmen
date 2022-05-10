package com.example.webservicelabb3.controller;

import com.example.webservicelabb3.model.Forecast;
import com.example.webservicelabb3.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForecastRest {

    @Autowired
    ForecastService service;

    @GetMapping("/rs/bestweather")
        public Forecast bestWeather() {
        return service.bestWeather();
    }

    @GetMapping("/rs/bestweatherlist")
    public List<Forecast> bestWeatherList() {
        return service.bestWeatherList();
    }
}
