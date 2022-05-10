package com.example.webservicelabb3.meteo;

import com.example.webservicelabb3.meteo.Meteo;
import com.example.webservicelabb3.smhi.SMHI;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MeteoRest {

    RestTemplate restTemplate = new RestTemplate();

    public Meteo getForecast() {
        Meteo meteo = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,windspeed_10m&windspeed_unit=ms", Meteo.class);
        return meteo;
    }

    public Double getTemperature() {
        return getForecast().getHourly().getTemperature2m().get(34);
    }

    public Double getWindSpeed() {
        return getForecast().getHourly().getWindspeed10m().get(34);
    }

    public String getTime() {
        return getForecast().getHourly().getTime().get(34) + ":00Z";
    }
}
