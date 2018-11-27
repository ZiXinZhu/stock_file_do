package com.zzx.stock_file_do.controller;

import com.zzx.stock_file_do.dao.HistoryDao;
import com.zzx.stock_file_do.entity.StockDay;
import com.zzx.stock_file_do.entity.StockEntity;
import com.zzx.stock_file_do.server.StockServer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr.John on 2018/11/15 11:00.
 **/
@Component
@ConfigurationProperties(prefix = "history")
@Data
public class HistoryCenter implements Runnable {

    @Autowired
    HistoryDao historyDao;
    @Autowired
    StockServer stockServer;

    private String TIME;

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    @Override
    public void run() {

        A:
        for (int i = 1; i <=4785; i+=318) {
            List<StockDay> list = new ArrayList<>();
            StockDay stockDay1;
            for (int j = i; j < i + 318; j++) {

                StockEntity stockEntity = historyDao.getID(j);
                //需要判断
                if(stockEntity==null){
                    break A;
                }
                String id = stockEntity.getStockId();
                String stockName = stockEntity.getStockName();
                //获取前天的历史数据
                Date da = new Date();
                Calendar now = Calendar.getInstance();
                now.setTime(da);
                now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
                da = now.getTime();
                DateFormat dateFormatt = new SimpleDateFormat("yyyy-MM-dd");
                String nowString = dateFormatt.format(da);

                URL ur = null;
                try {
                    //搜狐股票行情历史接口
                  //  ur = new URL("http://q.stock.sohu.com/hisHq?code=cn_" + id + "&start=19900101&end=20181126&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp");
                    ur = new URL("http://q.stock.sohu.com/hisHq?code=cn_" + id + "&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp");
                    //新浪股票行情历史接口
                    //     ur = new URL("http://biz.finance.sina.com.cn/stock/flash_hq/kline_data.php?&rand=random(10000)&symbol=sh600000&end_date=20150809&begin_date=20000101&type=plain");
                    HttpURLConnection uc = (HttpURLConnection) ur.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream(), "GBK"));
                    String s = reader.readLine();
                    list.removeAll(list);
                    if (s.length() > 100) {
                        s = s.substring(40);
                        while (s.length() > 0) {
                            String timener = TIME;
                            String stock = id;
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String str = s.substring(s.indexOf("\"") + 1, s.indexOf(",") - 1);
                            Date date1 = dateFormat.parse(str);
                            //比较时间
                           if (date1.compareTo(da) == -1) {
                                break;
                           }
                            //测试
                         /*   if (!str.equals("2018-11-26")) {
                                break;
                            }*/
                            s = s.substring(s.indexOf(",") + 2);
                            float opening = 0;
                            String stri = s.substring(0, s.indexOf(",") - 1);
                            opening = Float.parseFloat(stri);
                            s = s.substring(s.indexOf(",") + 2);
                            float price = 0;
                            price = Float.parseFloat(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            float chang = 0;
                            chang = Float.parseFloat(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            float chg = 0;
                            String strin = s.substring(0, s.indexOf(",") - 1);
                            NumberFormat numberFormat = NumberFormat.getPercentInstance();
                            Number number = numberFormat.parse(strin);
                            float d = (float) number.doubleValue();
                            chg = d;
                            s = s.substring(s.indexOf(",") + 2);
                            float low = 0;
                            low = Float.parseFloat(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            float high = 0;
                            high = Float.parseFloat(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            double trading_value = 0;
                            trading_value = Double.parseDouble(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            double turnover = 0;
                            turnover = Double.parseDouble(s.substring(0, s.indexOf(",") - 1));
                            s = s.substring(s.indexOf(",") + 2);
                            float turnover_rate = 0;
                            String string = s.substring(0, s.indexOf(",") - 2);
                            if (string.equals("-")) {
                                turnover_rate = 0;
                            } else {
                                NumberFormat num = NumberFormat.getPercentInstance();
                                Number nu = num.parse(string);
                                float dob = (float) number.doubleValue();
                                turnover_rate = dob;
                            }

                            //     System.out.println(s.indexOf(","));
                            if (s.indexOf(",") == 8) {
                                break;
                            } else {
                                s = s.substring(s.indexOf(",") + 2);
                                System.out.println(date1 + "," + opening + price + chang + chg + low + high + trading_value + turnover + turnover_rate);
                            }

                            //foreach批处理
                            stockDay1 = new StockDay();
                            stockDay1.setTradingValue(trading_value);
                            stockDay1.setTurnoverRate(turnover_rate);
                            stockDay1.setTurnover(turnover);
                            stockDay1.setTmv(0.0);
                            stockDay1.setStockName(stockName);
                            stockDay1.setPrice(price);
                            stockDay1.setPeRatio((float) 0.0);
                            stockDay1.setPbRatio((float) 0.0);
                            stockDay1.setOpening(opening);
                            stockDay1.setLow(low);
                            stockDay1.setHigh(high);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date1);
                            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
                            date1 = calendar.getTime();
                            stockDay1.setDate(date1);
                            stockDay1.setCmv(0.0);
                            stockDay1.setClose((float) 0.0);
                            stockDay1.setChg(chg);
                            stockDay1.setChang(chang);
                            stockDay1.setAmplitude((float) 0.0);
                            stockDay1.setStock(id);
                            list.add(stockDay1);
                        }
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }if(list.size()!=0){
                    if(j<=1875){
                        stockServer.insert(list);
                    }else {
                        stockServer.insertSZ(list);
                    }

                }

            }
        }
    }
}
