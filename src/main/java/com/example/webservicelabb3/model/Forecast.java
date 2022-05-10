package com.example.webservicelabb3.model;


public class Forecast implements Comparable<Forecast>{

    private String origin;

    private double temperature;

    private double windSpeed;

    private String time;


    public Forecast() {
    }

    public Forecast(String origin, double temperature, double windSpeed, String time) {
        this.origin = origin;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.time = time;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{”origin”:" + origin + ", ”temp”:"+ temperature +", ”windspeed”:" + windSpeed + ", timestamp:" + time + "}";
    }

    @Override
    public int compareTo(Forecast f) {
        if (temperature < f.temperature)
            return -1;
        else if(temperature > f.temperature)
            return 1;
        else if (windSpeed < f.windSpeed)
            return 1;
        else if(windSpeed > f.windSpeed)
            return -1;
        else
        return 0;
    }
}
