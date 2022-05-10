package com.example.webservicelabb3.controller;


import com.example.webservicelabb3.model.Forecast;
import com.example.webservicelabb3.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ForecastController {

    @Autowired
    ForecastService service;

    @GetMapping("/bestweather")
    public String bestWeather(Model model) {
        Forecast bestWeather = service.bestWeather();
        model.addAttribute("bestweather", bestWeather);
        return "bestweather";
    }

    @GetMapping("/bestweatherlist")
    public String bestWeatherList(Model model) {
        List<Forecast> bestWeatherList = service.bestWeatherList();
        model.addAttribute("bestweatherlist", bestWeatherList);
        return "bestweatherlist";
    }

}
