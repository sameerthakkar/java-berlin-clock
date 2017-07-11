package com.ubs.opsit.interviews.tdd;

import com.ubs.opsit.interviews.BerlinTimeConverter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.ubs.opsit.interviews.utils.TimeUtil.*;

import static org.junit.Assert.*;
import static com.ubs.opsit.interviews.utils.Constants.*;

public class BerlinTimeConverterTest {

    private static BerlinTimeConverter berlinTimeConverter;
    private String time;


    @BeforeClass
    public static void oneTimeSetup(){
        berlinTimeConverter = new BerlinTimeConverter();
    }

    @Before
    public void setUp(){
        time = "01:02:01";
    }

    @Test
    public void testTopLampOperation(){
        char lampStatus = berlinTimeConverter.getTopLampStatus(getSeconds(time));
        assertTrue(lampStatus == OFF);

        time = "01:02:00";
        lampStatus = berlinTimeConverter.getTopLampStatus(getSeconds(time));
        assertTrue(lampStatus == YELLOW);
    }

    @Test
    public void testHoursFirstRowLampsStatus(){
        String lampStatus = berlinTimeConverter.getHoursFirstRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_LAMPS_OFF));

        time= "09:00:00";
        lampStatus = berlinTimeConverter.getHoursFirstRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase("ROOO"));

        time= "23:00:00";
        lampStatus = berlinTimeConverter.getHoursFirstRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_RED_LAMPS_ON));
    }

    @Test
    public void testHoursSecondRowLampsStatus(){
        String lampStatus = berlinTimeConverter.getHoursSecondRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase("ROOO"));

        time= "10:00:00";
        lampStatus = berlinTimeConverter.getHoursSecondRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_LAMPS_OFF));

        time= "19:00:00";
        lampStatus = berlinTimeConverter.getHoursSecondRowLampsStatus(getHours(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_RED_LAMPS_ON));
    }

    @Test
    public void testMinutesFirstRowLampsStatus(){
        String lampStatus = berlinTimeConverter.getMinutesFirstRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_ELEVEN_LAMPS_OFF));

        time= "10:24:59";
        lampStatus = berlinTimeConverter.getMinutesFirstRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase("YYRYOOOOOOO"));

        time= "19:46:00";
        lampStatus = berlinTimeConverter.getMinutesFirstRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase("YYRYYRYYROO"));
    }

    @Test
    public void testMinutesSecondRowLampsStatus(){
        String lampStatus = berlinTimeConverter.getMinutesSecondRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase("YYOO"));

        time= "10:10:59";
        lampStatus = berlinTimeConverter.getMinutesSecondRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_LAMPS_OFF));

        time= "19:44:00";
        lampStatus = berlinTimeConverter.getMinutesSecondRowLampsStatus(getMinutes(time));
        assertTrue(lampStatus.equalsIgnoreCase(ALL_FOUR_YELLOW_LAMPS_ON ));
    }

    @Test
    public void testConvertTimeWithInvalidTime(){
        time = "01";
        String result = berlinTimeConverter.convertTime(time);
        assertTrue(result.equalsIgnoreCase(INVALID_TIME_FORMAT));
    }

    @Test
    public void testConvertTime(){
        time = "23:59:59";
        String expectedResult = "O"+NEW_LINE+
                "RRRR"+NEW_LINE+
                "RRRO"+NEW_LINE+
                "YYRYYRYYRYY"+NEW_LINE+
                "YYYY";
        String result = berlinTimeConverter.convertTime(time);
        assertTrue(result.equalsIgnoreCase(expectedResult));
    }
}
