package com.www.skeleton;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TestCommon {

    @Test
    public void time(){
        System.out.println(new Date());

        System.out.println(TimeZone.getDefault());

        System.out.println(Calendar.getInstance().getTimeZone());
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());

        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC+1"));
        System.out.println(utc.getTime());
        System.out.println(utc.getTimeInMillis());

    }

}
