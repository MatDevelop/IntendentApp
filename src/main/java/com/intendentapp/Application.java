package com.intendentapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intendentapp.controller.MainController;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LogManager.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start aplikacji...");
    }
}
