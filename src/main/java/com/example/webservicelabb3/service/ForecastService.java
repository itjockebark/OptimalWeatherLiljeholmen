package com.example.webservicelabb3.service;


import com.example.webservicelabb3.met.MetRest;
import com.example.webservicelabb3.meteo.MeteoRest;
import com.example.webservicelabb3.smhi.SmhiRest;
import com.example.webservicelabb3.model.Forecast;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForecastService {

    SmhiRest smhiRest = new SmhiRest();

    MetRest metRest = new MetRest();

    MeteoRest meteoRest = new MeteoRest();

    Forecast smhiForecast = new Forecast("SMHI", smhiRest.getTemperature(), smhiRest.getWindSpeed(), smhiRest.getTime());
    Forecast metForecast = new Forecast("MET", metRest.getTemperature(), metRest.getWindSpeed(), metRest.getTime());
    Forecast meteoForecast = new Forecast("Meteo", meteoRest.getTemperature(), meteoRest.getWindSpeed(), meteoRest.getTime());

    List<Forecast> forecasts = new ArrayList<>(Arrays.asList(smhiForecast,metForecast,meteoForecast));

    public void sortForecasts() {
        forecasts.sort(Collections.reverseOrder());
    }

    public Forecast bestWeather() {
        sortForecasts();
        return forecasts.get(0);
    }

    public List<Forecast> bestWeatherList() {
        sortForecasts();
        return forecasts;
    }

}

