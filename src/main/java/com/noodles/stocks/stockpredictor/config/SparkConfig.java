package com.noodles.stocks.stockpredictor.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public JavaSparkContext sparkContext () {
        SparkConf sparkConf = new SparkConf().setAppName("TestWithObjects").setMaster("local");
        return new JavaSparkContext(sparkConf);
    }
}
