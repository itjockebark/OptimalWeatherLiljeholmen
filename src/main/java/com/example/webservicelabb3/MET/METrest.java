package com.example.webservicelabb3.MET;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class METrest {

    RestTemplate restTemplate= new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
            .build();

    public Met getForecast() {
        Met met = restTemplate.getForObject("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300", Met.class);
        return met;
    }

    public double getTemperature() {
        return getForecast().getProperties().getTimeseries().get(24).getData().getInstant().getDetails().getAirTemperature();
    }

    public double getWindSpeed() {
        return getForecast().getProperties().getTimeseries().get(24).getData().getInstant().getDetails().getWindSpeed();
    }

    public String getTime() {
        return getForecast().getProperties().getTimeseries().get(24).getTime();
    }


}
