package com.example.webservicelabb3.smhi;

import com.example.webservicelabb3.model.Forecast;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SmhiDAO {

    RestTemplate restTemplate = new RestTemplate();

    SMHI smhi = restTemplate.getForObject("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json",SMHI.class);

    public double getTemperature() {
        return smhi.getTimeSeries().get(getTimeIndex()).getParameters().get(getParameterIndex("t")).getValues().get(0);
    }

    public double getWindSpeed() {
        return smhi.getTimeSeries().get(getTimeIndex()).getParameters().get(getParameterIndex("ws")).getValues().get(0);
    }

    public String getTime() {
        return smhi.getTimeSeries().get(getTimeIndex()).getValidTime();
    }

    public Forecast getForecast() {
        return new Forecast("SMHI", getTemperature(), getWindSpeed(), getTime());
    }

    public int getParameterIndex(String name) {
        List<Parameter> parameters = smhi.getTimeSeries().get(getTimeIndex()).getParameters();
        int index = 0;

        for (int i = 0; i < parameters.size(); i++) {
            if(parameters.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public String getCurrentTime() {
        LocalDateTime ldtn = LocalDateTime.now(ZoneOffset.UTC).plusHours(24);
        DateTimeFormatter fmtr = DateTimeFormatter.ISO_DATE_TIME;
        return ldtn.truncatedTo(ChronoUnit.HOURS).format(fmtr) + "Z";
    }

    public int getTimeIndex() {
        String currentTime = getCurrentTime();
        List<TimeSeries> timeSeries = smhi.getTimeSeries();
        int index = 0;

        for (int i = 0; i < timeSeries.size(); i++) {
            if (timeSeries.get(i).getValidTime().equals(currentTime)) {
                index = i;
            }
        }
        return index;
    }
}
