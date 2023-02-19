package com.noodles.stocks.stockpredictor.models.schema;

import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.DataTypes;

public class StockPriceSchema {
    public static StructType getSchema() {
        return new StructType(new StructField[] {
                new StructField("date", DataTypes.DateType, false, null),
                new StructField("open", DataTypes.DoubleType, false, null),
                new StructField("high", DataTypes.DoubleType, false, null),
                new StructField("low", DataTypes.DoubleType, false, null),
                new StructField("close", DataTypes.DoubleType, false, null),
                new StructField("volume", DataTypes.LongType, false, null)
        });
    }
}

