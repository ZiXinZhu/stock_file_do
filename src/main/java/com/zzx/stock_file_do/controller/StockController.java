package com.zzx.stock_file_do.controller;

import com.zzx.stock_file_do.dao.HistoryDao;
import com.zzx.stock_file_do.dao.StockDao;
import com.zzx.stock_file_do.entity.StockDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.John on 2018/11/7 21:54.
 **/

@RestController
public class StockController {
    @Autowired
    StockDao stockDao;
    @Autowired
    HistoryDao historyDao;

    @GetMapping("/get")
    public byte[] set() throws IOException {


        FileInputStream fileinputstream=new FileInputStream("F:/shen.txt");
        int size=fileinputstream.available();
        byte[] arr=new byte[size];
        fileinputstream.read(arr);

        if(arr!=null){
            try {
                fileinputstream.close();
            }catch (Exception e){
                System.out.println("读取失败！");
            }
        }
        Map<String,String> map=new HashMap<>();

        String string=new String(arr);

        while (string.length()>0){
            int indexo=string.indexOf("(");
            int indext=string.indexOf(")");
            String key=string.substring(0,indexo);
            String val=string.substring(indexo+1,indext);
            map.put(key,val);
            if(indext!=string.lastIndexOf(")")){
                string=string.substring(indext+3);
            }else {
                break;
            }

        }

        for (Map.Entry<String,String>  m:map.entrySet()
             ) {
            stockDao.set(m.getKey(),m.getValue());
            System.out.println(m.getKey()+","+m.getValue());
        }
        return arr;

    }



    @RequestMapping("/getTime")
    public StockDay getTime(){
       StockDay h=historyDao.getTime(855799);
       return  h;
    }
}
