package com.noodles.stocks.stockpredictor.controller;

import com.noodles.stocks.stockpredictor.session.StockPriceDataSession;
import com.noodles.stocks.stockpredictor.service.impl.StockPricePredictorImpl;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/stock-price")
public class StockPriceController {
    @Autowired
    private StockPriceDataSession stockPriceDataSession;

    @Autowired
    private StockPricePredictorImpl stockPricePredictor;

    @PostMapping("/train")
    public void train(@RequestParam String symbol) throws IOException {
        Dataset<Row> data = stockPriceDataSession.loadData(symbol);
        this.stockPricePredictor.train(data);
    }

    @GetMapping("/predict")
    public double predict(@RequestParam String symbol) {
        return this.stockPricePredictor.predict(symbol);
    }
}