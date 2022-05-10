package com.example.webservicelabb3.smhi;

import com.example.webservicelabb3.met.Timeseries;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SmhiRest {

    RestTemplate restTemplate = new RestTemplate();

    public SMHI getForecast() {
        SMHI smhi = restTemplate.getForObject("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json",SMHI.class);
        return smhi;
    }

    public double getTemperature() {
        return getForecast().getTimeSeries().get(getTimeIndex()).getParameters().get(1).getValues().get(0);
    }

    public double getWindSpeed() {
        return getForecast().getTimeSeries().get(getTimeIndex()).getParameters().get(4).getValues().get(0);
    }

    public String getTime() {
        return getForecast().getTimeSeries().get(getTimeIndex()).getValidTime();
    }


    public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr) + "Z";
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<TimeSeries> timeSeries = getForecast().getTimeSeries();
        int index = 0;

        for (int i = 0; i < timeSeries.size(); i++) {
            if (timeSeries.get(i).getValidTime().equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
