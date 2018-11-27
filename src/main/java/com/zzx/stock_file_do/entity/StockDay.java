package com.zzx.stock_file_do.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockDay {
    private Integer uuid;

    private String stock;

    private String stockName;

    private Date date;

    private Float price;

    private Float chg;

    private Float chang;

    private Float opening;

    private Float high;

    private Float low;

    private Float close;

    private Double tradingValue;

    private Double turnover;

    private Double tmv;

    private Double cmv;

    private Float amplitude;

    private Float turnoverRate;

    private Float pbRatio;

    private Float peRatio;

    List<StockDay> list=new ArrayList<>();

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getChg() {
        return chg;
    }

    public void setChg(Float chg) {
        this.chg = chg;
    }

    public Float getChang() {
        return chang;
    }

    public void setChang(Float chang) {
        this.chang = chang;
    }

    public Float getOpening() {
        return opening;
    }

    public void setOpening(Float opening) {
        this.opening = opening;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Double getTradingValue() {
        return tradingValue;
    }

    public void setTradingValue(Double tradingValue) {
        this.tradingValue = tradingValue;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Double getTmv() {
        return tmv;
    }

    public void setTmv(Double tmv) {
        this.tmv = tmv;
    }

    public Double getCmv() {
        return cmv;
    }

    public void setCmv(Double cmv) {
        this.cmv = cmv;
    }

    public Float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Float amplitude) {
        this.amplitude = amplitude;
    }

    public Float getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Float turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Float getPbRatio() {
        return pbRatio;
    }

    public void setPbRatio(Float pbRatio) {
        this.pbRatio = pbRatio;
    }

    public Float getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(Float peRatio) {
        this.peRatio = peRatio;
    }
}