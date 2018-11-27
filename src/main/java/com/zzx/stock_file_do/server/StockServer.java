package com.zzx.stock_file_do.server;

import com.zzx.stock_file_do.dao.StockDayMapper;
import com.zzx.stock_file_do.entity.StockDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.John on 2018/11/25 17:03.
 **/
@Service
public class StockServer {
    @Autowired
    StockDayMapper stockDayMapper;

    private List<StockDay> stockDay;

    public void insert(List<StockDay> stockDay){
        this.stockDay = stockDay;
        stockDayMapper.insert(stockDay);
    }
    public void insertSZ(List<StockDay> stockDay){
        this.stockDay=stockDay;
        stockDayMapper.setsz(stockDay);
    }
}
