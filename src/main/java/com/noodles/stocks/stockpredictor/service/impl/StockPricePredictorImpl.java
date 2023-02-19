package com.noodles.stocks.stockpredictor.service.impl;

import com.noodles.stocks.stockpredictor.models.StockData;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.spark.sql.functions.col;

@Service
public class StockPricePredictorImpl {

    private LinearRegressionModel model;

    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private JavaSparkContext javaSparkContext;

    @Autowired
    private StockDataDownloader stockDataDownloader;

    public void train(Dataset<Row> data) {
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[] {
                        "Time Series (Daily)",
//                        "1. open",
//                        "2. high",
//                        "3. low",
//                        "4. close",
//                        "5. adjusted close",
//                        "6. volume",
//                        "7. dividend amount",
//                        "8. split coefficient"
                })
                .setOutputCol("features");

        Dataset<Row> trainingData = assembler.transform(data)
                .select(col("1. open").as("open"), col("features"));

        LinearRegression linearRegression = new LinearRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0.8);

        this.model = linearRegression.fit(trainingData);
    }

    public double predict(String symbol) {
        //Load List Of StockData
        List<StockData> stockDataList = stockDataDownloader.downloadStockData(symbol);

        //Convert StockData To a JavaRDD dataset.
        JavaRDD<StockData> rowRDD = javaSparkContext.parallelize(stockDataList);

        //Create Spark DataFrame W/ StockData.
        Dataset<Row> data = sparkSession.createDataFrame(rowRDD, StockData.class);

        //Compute Predictions
        Dataset<Row> prediction = this.model.transform(data)
                .select(functions.col("prediction"));

        prediction.show();
        prediction.printSchema();

        return prediction.head().getDouble(0);
    }
}
