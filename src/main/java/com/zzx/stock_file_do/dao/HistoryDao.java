package com.zzx.stock_file_do.dao;

import com.zzx.stock_file_do.entity.StockDay;
import com.zzx.stock_file_do.entity.StockEntity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.John on ${DATE} ${TIME}.
 **/

@Mapper
public interface HistoryDao {

    @Insert("INSERT INTO stock_day (uuid,stock,stock_name,date,price,chg,chang,opening,high,low,close,trading_value," +
            "turnover,tmv,cmv,amplitude,turnover_rate,pb_ratio,pe_ratio) values (#{uuid},#{stock},#{stockName},#{date}," +
            "#{price},#{chg},#{chang},#{opening},#{high},#{low},#{close},#{tradingValue},#{turnover},#{tmv},#{cmv},#{amplitude}," +
            "#{turnoverRate},#{pbRatio},#{peRatio})")
    int history(
            @Param("uuid") int uuid, @Param("stock") String stock,
            @Param("stockName") String stockName, @Param("date") Date date,
            @Param("price") float price, @Param("chg") float chg,
            @Param("chang") float chang, @Param("opening") float opening,
            @Param("high") float high, @Param("low") float low,
            @Param("close") float close, @Param("tradingValue") Double tradingValue,
            @Param("turnover") Double turnover, @Param("tmv") Double tmv,
            @Param("cmv") Double cmv, @Param("amplitude") float amplitude,
            @Param("turnoverRate") float turnoverRate, @Param("pbRatio") float pbRatio,
            @Param("peRatio") float peRatio);

    @Insert("INSERT INTO stock_day  (date,price,chg,chang,opening,high,low,trading_value," +
            "turnover,turnover_rate,stock,stock_name) VALUES(#{date},#{price},#{chg},#{chang},#{opening},#{high},#{low},#{tradingValue}" +
            ",#{turnover},#{turnoverRate},#{stock},#{stockName}) ")
    int setHistory(
            @Param("date") Date date, @Param("price") float price,
            @Param("chg") float chg, @Param("chang") float chang,
            @Param("opening") float opening, @Param("high") float high,
            @Param("low") float low, @Param("tradingValue") Double tradingValue,
            @Param("turnover") Double turnover, @Param("turnoverRate") float turnoverRate,
            @Param("stock")String stock,@Param("stockName")String name);

    @Select("SELECT * from  stock_info where id=#{id} ")
    StockEntity getID(@Param("id") int id);
    @Select("SELECT * from  stock_day where stock=#{stock} ")
    List<StockDay> getTime(@Param("stock") String stock);
}
