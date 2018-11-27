package com.zzx.stock_file_do.dao;

import com.zzx.stock_file_do.entity.StockDay;

import java.util.List;

public interface StockDayMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(List<StockDay> list);
    int setsz(List<StockDay> list);

    int insertSelective(StockDay record);

    StockDay selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(StockDay record);

    int updateByPrimaryKey(StockDay record);
}