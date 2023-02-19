package com.noodles.stocks.stockpredictor.models;

public class StockData {
    private String symbol;
    private String name;

    private double open;
    private double high;
    private double low;
    private double close;
    private double adjustedClose;

    private double volume;

    private double dividendAmount;

    private double splitCoefficient;

    public StockData() {
    }

    public StockData(String symbol, String name,
                     double open, double high, double low, double close,
                     double adjustedClose, double volume, double dividendAmount, double splitCoefficient) {
        this.symbol = symbol;
        this.name = name;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
        this.dividendAmount = dividendAmount;
        this.splitCoefficient = splitCoefficient;

    }

    public StockData(String symbol, String name,
                     double open, double high, double low, double close,
                     double volume) {
        this.symbol = symbol;
        this.name = name;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getDividendAmount() {
        return dividendAmount;
    }

    public void setDividendAmount(double dividendAmount) {
        this.dividendAmount = dividendAmount;
    }

    public double getSplitCoefficient() {
        return splitCoefficient;
    }

    public void setSplitCoefficient(double splitCoefficient) {
        this.splitCoefficient = splitCoefficient;
    }
}
