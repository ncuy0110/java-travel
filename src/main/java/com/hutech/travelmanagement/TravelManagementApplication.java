package com.hutech.travelmanagement;

import com.hutech.travelmanagement.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TravelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
    }
}
