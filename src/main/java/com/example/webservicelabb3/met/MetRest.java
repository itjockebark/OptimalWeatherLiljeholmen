package com.example.webservicelabb3.met;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MetRest {

    RestTemplate restTemplate= new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
            .build();

    public Met getForecast() {
        Met met = restTemplate.getForObject("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300", Met.class);
        return met;
    }

    public double getTemperature() {
        return getForecast().getProperties().getTimeseries().get(getTimeIndex()).getData().getInstant().getDetails().getAirTemperature();
    }

    public double getWindSpeed() {
        return getForecast().getProperties().getTimeseries().get(getTimeIndex()).getData().getInstant().getDetails().getWindSpeed();
    }

    public String getTime() {
        return getForecast().getProperties().getTimeseries().get(getTimeIndex()).getTime();
    }

    public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr) + "Z";
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<Timeseries> timeSeries = getForecast().getProperties().getTimeseries();
        int index = 0;

        for (int i = 0; i < timeSeries.size(); i++) {
            if (timeSeries.get(i).getTime().equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
