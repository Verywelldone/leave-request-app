package com.marius.leaverequestgestionapp.service;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class LeaveRequestImplTest {


    @Test
    void testDate() throws ParseException {


        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//        Date firstDate = sdf.parse("Fri Jan 08 00:00:00 EET 2021");
        Date firstDate = new Date();
        Date secondDate = sdf.parse("Sun Jan 14 00:00:00 EET 2021");

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        assertEquals(6, diff);
    }

}