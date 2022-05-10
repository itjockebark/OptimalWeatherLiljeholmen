package com.example.webservicelabb3.meteo;

import com.example.webservicelabb3.met.Timeseries;
import com.example.webservicelabb3.meteo.Meteo;
import com.example.webservicelabb3.smhi.SMHI;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MeteoRest {

    RestTemplate restTemplate = new RestTemplate();

    public Meteo getForecast() {
        Meteo meteo = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,windspeed_10m&windspeed_unit=ms", Meteo.class);
        return meteo;
    }

    public Double getTemperature() {
        return getForecast().getHourly().getTemperature2m().get(getTimeIndex());
    }

    public Double getWindSpeed() {
        return getForecast().getHourly().getWindspeed10m().get(getTimeIndex());
    }

    public String getTime() {
        return getForecast().getHourly().getTime().get(getTimeIndex()) + ":00Z";
    }

    public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr).substring(0,16);
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<String> time = getForecast().getHourly().getTime();
        int index = 0;

        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
