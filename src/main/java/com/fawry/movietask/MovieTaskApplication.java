package com.fawry.movietask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.HttpURLConnection;

@SpringBootApplication
@EnableFeignClients
public class MovieTaskApplication {

    // backend call
    //

    public static void main(String[] args) {
        SpringApplication.run(MovieTaskApplication.class, args);
     }

}
