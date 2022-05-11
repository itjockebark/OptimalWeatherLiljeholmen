package com.example.webservicelabb3.met;

import com.example.webservicelabb3.model.Forecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MetDAO {

    RestTemplate restTemplate= new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
            .build();

    Met met = restTemplate.getForObject("https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300", Met.class);

    public double getTemperature() {
        return met.getProperties().getTimeseries().get(getTimeIndex()).getData().getInstant().getDetails().getAirTemperature();
    }

    public double getWindSpeed() {
        return met.getProperties().getTimeseries().get(getTimeIndex()).getData().getInstant().getDetails().getWindSpeed();
    }

    public String getTime() {
        return met.getProperties().getTimeseries().get(getTimeIndex()).getTime();
    }

    public Forecast getForecast() {
        return new Forecast("MET", getTemperature(), getWindSpeed(), getTime());
    }

   public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr) + "Z";
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<Timeseries> timeSeries = met.getProperties().getTimeseries();
        int index = 0;

        for (int i = 0; i < timeSeries.size(); i++) {
            if (timeSeries.get(i).getTime().equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
