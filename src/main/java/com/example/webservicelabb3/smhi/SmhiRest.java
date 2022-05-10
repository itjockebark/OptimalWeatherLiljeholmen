package com.example.webservicelabb3.smhi;

import org.springframework.web.client.RestTemplate;

public class SmhiRest {

    RestTemplate restTemplate = new RestTemplate();

    public SMHI getForecast() {
        SMHI smhi = restTemplate.getForObject("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json",SMHI.class);
        return smhi;
    }

    public double getTemperature() {
        return getForecast().getTimeSeries().get(23).getParameters().get(1).getValues().get(0);
    }

    public double getWindSpeed() {
        return getForecast().getTimeSeries().get(23).getParameters().get(4).getValues().get(0);
    }

    public String getTime() {
        return getForecast().getTimeSeries().get(23).getValidTime();
    }
}
