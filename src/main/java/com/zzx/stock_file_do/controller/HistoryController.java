package com.zzx.stock_file_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mr.John on 2018/11/8 13:36.
 **/
@RestController
public class HistoryController {

    @Autowired
    HistoryCenter historyCenter;

    @GetMapping("/history")
    @Scheduled(cron = "59 59 21 * * ?")
    public void history() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(14);
        long time1 = System.currentTimeMillis();

        for (int j = 0; j < 14; j++) {
            executorService.submit(historyCenter);
        }

        //主线程不等待子线程如上
        //让主线程等子线程如下
        executorService.shutdown();//关闭线程池,不会真的关闭,只是不接收新任务
        while (true) {
            //判断是否所有线程都执行完毕
            if (executorService.isTerminated()) {
                System.out.println("结束了！");
                long time2 = System.currentTimeMillis();
                System.out.println("花费时间:" + (time2 - time1));
                break;
            }
            Thread.sleep(20);//防止循环很多次
        }

        //测试
/*        StockDay stockDay = new StockDay();
        stockDay.setUuid(123);
        stockDay.setAmplitude((float) 2.32);
        stockDay.setChang((float) 9.15);
        stockDay.setChg((float) 1.36);
        stockDay.setClose((float) 2.63);
        stockDay.setCmv(3.25);
        Date date = new Date(System.currentTimeMillis());
        stockDay.setDate(date);
        stockDay.setHigh((float) 6.23);
        stockDay.setLow((float) 4.26);
        stockDay.setOpening((float) 3.78);
        stockDay.setPbRatio((float) 2.89);
        stockDay.setPeRatio((float) 2.95);
        stockDay.setPrice((float) 1.75);
        stockDay.setStock("126523");
        stockDay.setStockName("name");
        stockDay.setTmv(5.24);
        stockDay.setTradingVolue(5.48);
        stockDay.setTurnover(9.56);
        stockDay.setTurnoverRate((float) 2.32);
        int his=historyDao.history(stockDay.getUuid(),stockDay.getStock(),stockDay.getStockName(),stockDay.getDate(),stockDay.getPrice(),stockDay.getChg(),stockDay.getChang(),stockDay.getOpening(),
                stockDay.getHigh(),stockDay.getLow(),stockDay.getClose(),stockDay.getTradingVolue(),stockDay.getTurnover(),stockDay.getTmv(),stockDay.getCmv(),stockDay.getAmplitude(),stockDay.getTurnoverRate()
                ,stockDay.getPbRatio(),stockDay.getPeRatio());*/

    }
}
