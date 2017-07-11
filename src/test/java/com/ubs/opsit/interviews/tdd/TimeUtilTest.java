package com.ubs.opsit.interviews.tdd;

import utils.TimeUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 7/9/2017.
 */
public class TimeUtilTest {

    private String time;

    @Before
    public void setUp(){
        time = "01:02:03";
    }

    @Test
    public void testTimeConversion(){
        int secs = TimeUtil.getSeconds(time);
        assertTrue(secs == 03);

        int mins = TimeUtil.getMinutes(time);
        assertTrue(mins == 02);

        int hours = TimeUtil.getHours(time);

        assertTrue(hours == 01);
    }


    @Test
    public void testValidateTime(){
        time = "00:00:00";
        assertTrue(TimeUtil.validateTime(time));

        time = "00:00:60";
        assertFalse(TimeUtil.validateTime(time));

        time = "00:00:59";
        assertTrue(TimeUtil.validateTime(time));

        time = "00:60:59";
        assertFalse(TimeUtil.validateTime(time));

        time = "00:59:59";
        assertTrue(TimeUtil.validateTime(time));

        time = "24:59:59";
        assertTrue(TimeUtil.validateTime(time));

        time = "23:59:59";
        assertTrue(TimeUtil.validateTime(time));

        time = "26:14:55";
        assertFalse(TimeUtil.validateTime(time));

        time = "00:00";
        assertFalse(TimeUtil.validateTime(time));

        time = "00";
        assertFalse(TimeUtil.validateTime(time));
    }


}
