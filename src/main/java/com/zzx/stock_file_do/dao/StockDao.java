package com.zzx.stock_file_do.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mr.John on 2018/11/7 21:53.
 **/
@Mapper
public interface StockDao {
    @Insert("INSERT INTO stock_day (stock_name,stock)values (#{stockName},#{stockId})")
    int set(@Param("stockName")String stockName,
            @Param("stockId")String stockId
            );
}
