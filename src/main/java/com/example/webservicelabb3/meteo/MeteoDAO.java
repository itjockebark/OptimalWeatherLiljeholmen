package com.example.webservicelabb3.meteo;
import com.example.webservicelabb3.model.Forecast;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MeteoDAO {

    RestTemplate restTemplate = new RestTemplate();

    Meteo meteo = restTemplate.getForObject("https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,windspeed_10m&windspeed_unit=ms", Meteo.class);

    public Double getTemperature() {
        return meteo.getHourly().getTemperature2m().get(getTimeIndex());
    }

    public Double getWindSpeed() {
        return meteo.getHourly().getWindspeed10m().get(getTimeIndex());
    }

    public String getTime() {
        return meteo.getHourly().getTime().get(getTimeIndex()) + ":00Z";
    }

    public Forecast getForecast() {
        return new Forecast("Meteo", getTemperature(), getWindSpeed(), getTime());
    }

    public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr).substring(0,16);
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<String> time = meteo.getHourly().getTime();
        int index = 0;

        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
