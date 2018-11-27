package com.zzx.stock_file_do;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.John on 2018/11/8 21:25.
 **/
public class Do {
    public static void main(String[] args) throws ParseException {
        /*String s1="1.4%";
        NumberFormat nf= NumberFormat.getPercentInstance();
        Number n=nf.parse(s1);
        System.out.println
(n);*/
        String a="1234";
        String b="1234";
        String c=new String("1234");

        System.out.println(a==b);
        System.out.println(a==c);

        List list=new ArrayList();
        List list1=list;
        list1=null;
        System.out.println("");



    }

}
