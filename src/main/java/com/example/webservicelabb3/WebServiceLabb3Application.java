package com.example.webservicelabb3;

import com.example.webservicelabb3.MET.METrest;
import com.example.webservicelabb3.SMHI.SMHIrest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServiceLabb3Application {

    public static void main(String[] args) {
       // SpringApplication.run(WebServiceLabb3Application.class, args);

        SMHIrest smhiRest = new SMHIrest();

        METrest metRest = new METrest();

        //System.out.println(smhIrest.getForecast());

        System.out.println(smhiRest.getTime());
        System.out.println(metRest.getTime());
    }
}
