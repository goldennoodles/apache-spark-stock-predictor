package com.noodles.stocks.stockpredictor.config;

import org.apache.spark.internal.config.R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
