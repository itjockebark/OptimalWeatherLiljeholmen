package com.example.webservicelabb3.service;

import com.example.webservicelabb3.met.MetDAO;
import com.example.webservicelabb3.meteo.MeteoDAO;
import com.example.webservicelabb3.smhi.SmhiDAO;
import com.example.webservicelabb3.model.Forecast;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ForecastService {

    SmhiDAO smhiDAO = new SmhiDAO();

    MetDAO metDAO = new MetDAO();

    MeteoDAO meteoDAO = new MeteoDAO();

    Forecast smhiForecast = smhiDAO.getForecast();
    Forecast metForecast = metDAO.getForecast();
    Forecast meteoForecast = meteoDAO.getForecast();

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

