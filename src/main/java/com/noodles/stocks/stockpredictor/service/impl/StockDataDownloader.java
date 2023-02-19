package com.noodles.stocks.stockpredictor.service.impl;

import com.noodles.stocks.stockpredictor.models.StockData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class StockDataDownloader {

    private final RestTemplate restTemplate;

    @Value("${alpha.token.key")
    private String apiToken;

    @Autowired
    public StockDataDownloader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockData> downloadStockData(String stockSymbol) {

        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + stockSymbol + "&apikey=" + apiToken;

        List<StockData> stockDataList = new ArrayList<>();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();
            JSONObject json = new JSONObject(responseBody);
            System.out.println("API-CALL RESPONSE: " + json);

            JSONObject timeSeriesData = json.getJSONObject("Time Series (Daily)");

            Iterator<String> dates = timeSeriesData.keys();
            while (dates.hasNext()) {
                String date = dates.next();
                JSONObject stockDataJson = timeSeriesData.getJSONObject(date);
                StockData stockData = new StockData(stockSymbol, date,
                        stockDataJson.getDouble("1. open"),
                        stockDataJson.getDouble("2. high"),
                        stockDataJson.getDouble("3. low"),
                        stockDataJson.getDouble("4. close"),
                        stockDataJson.getDouble("5. adjusted close"),
                        stockDataJson.getDouble("6. volume"),
                        stockDataJson.getDouble("7. dividend amount"),
                        stockDataJson.getDouble("8. split coefficient"));

                stockDataList.add(stockData);
            }

            Collections.reverse(stockDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stockDataList;
    }

    public String getJsonStringFromAlpha (String stockSymbol) {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + stockSymbol + "&apikey=" + apiToken;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
