package com.example.demo.util;

import java.util.Calendar;
import java.util.Date;

public class DBPush {

    public static void main(String[] args) {
        int size = 3;
        if(size > 60){
            size = 1;
        }else {
            while (true){
                if(60 % size == 0){
                    break;
                }
                size++;
            }
        }
        System.out.println("size " + size);
        int length = 60 / size;
        int[] split = new int[length];
        split[0] = size;
        for (int i = 1; i < length; i++) {
            split[i] = split[i-1] + size;
        }
        // String date = "2020-03-22 20:55:40";
        long time = 1584720000L;
        Date date = new Date(time * 1000);
        int minuteByDate = getMinuteByDate(date);
        int secondByDate = getSecondByDate(date);
        System.out.println("minuteByDate = " + minuteByDate);
        System.out.println("secondByDate = " + secondByDate);
        long dbTime = 0;
        long dbTimeEarlyOne = 0L;
        if(minuteByDate < split[0]){
            int i1 = split[0] - minuteByDate;
            dbTime = time + i1 * 60 - secondByDate;
            dbTimeEarlyOne = dbTime - split[0] * 60;
        }else {
            for (int i = 0; i < length - 1; i++) {
                if(minuteByDate > split[i] && minuteByDate <= split[i+1]){
                    int i1 = split[i + 1] - minuteByDate;
                    dbTime = time + i1 * 60 - secondByDate;
                    dbTimeEarlyOne = dbTime - split[i + 1] * 60;
                    break;
                }
            }
        }

        System.out.println("dbTime = " + dbTime + "    ,dbTimeEarlyOne = " + dbTimeEarlyOne );

        /**
         *  10 20 30 40 50 60
         *
         *
         */

        /**
         *  00:10:00
         *  00:20:00
         *  00:30:00
         *  00:40:00
         *  00:50:00
         *  00:00:00
         */




    }

    public static int getMinuteByDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.MINUTE);
    }

    public static int getSecondByDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.SECOND);
    }

    public static int getHourByDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.HOUR);
    }
}
