package com.noodles.stocks.stockpredictor.session;
import com.noodles.stocks.stockpredictor.models.schema.StockPriceSchema;
import com.noodles.stocks.stockpredictor.service.impl.StockDataDownloader;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.DatasetHolder;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class StockPriceDataSession {

    @Autowired
    StockDataDownloader stockDataDownloader;

    @Autowired
    private JavaSparkContext sparkContext;

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("Stock Price Predictor")
                .master("local[*]")
                .getOrCreate();
    }

    public Dataset<Row> loadData(String symbol) throws IOException {
        String stockDataJson = stockDataDownloader.getJsonStringFromAlpha(symbol);
        File file = File.createTempFile("temp",".json");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(stockDataJson);
        bw.close();

        System.out.println("File Path: " + file.getAbsolutePath());

        return this.sparkSession().read()
                .option("header", true)
                .option("inferSchema", true)
                .option("multiline", true)
//                .schema(StockPriceSchema.getSchema())
                .json(file.getAbsolutePath());
    }

}
