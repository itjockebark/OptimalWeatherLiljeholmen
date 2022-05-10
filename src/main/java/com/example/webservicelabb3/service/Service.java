package com.example.webservicelabb3.service;


import com.example.webservicelabb3.MET.METrest;
import com.example.webservicelabb3.SMHI.SMHIrest;

public class Service {

    SMHIrest smhiRest = new SMHIrest();

    METrest metRest = new METrest();

    public String bestWeather() {
        double smhiTemperature = smhiRest.getTemperature();
        double metTemperature = metRest.getTemperature();


        double smhiWindSpeed = smhiRest.getWindSpeed();
        double metWindSpeed = metRest.getWindSpeed();

        String smhiTime = smhiRest.getTime();
        String metTime = metRest.getTime();
        if(smhiTemperature > metTemperature) {

            return "{”origin”: ”SMHI”, ”temp”:"+ smhiTemperature +", ”windspeed”:" + smhiWindSpeed+ ", timestamp:" + smhiTime + "}";
        }
        else if (smhiTemperature < metTemperature)
        {
            return "{”origin”: ”MET”, ”temp”:"+ metTemperature +", ”windspeed”:" + metWindSpeed+ ", timestamp:" + metTime + "}";
        }
        else
            if(smhiWindSpeed > metWindSpeed) {
                return "{”origin”: ”MET”, ”temp”:"+ metTemperature +", ”windspeed”:" + metWindSpeed+ ", timestamp:" + metTime + "}";
            }
            else
                return "{”origin”: ”SMHI”, ”temp”:"+ smhiTemperature +", ”windspeed”:" + smhiWindSpeed+ ", timestamp:" + smhiTime + "}";
    }

}

