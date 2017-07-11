package com.ubs.opsit.interviews;

import static utils.Constants.*;
import static utils.TimeUtil.*;

public class BerlinTimeConverter implements TimeConverter {

    @Override
    public String convertTime(String time) {
        if (validateTime(time)) {
            StringBuilder sb = new StringBuilder();
            sb.append(getTopLampStatus(getSeconds(time))).append(NEW_LINE);
            sb.append(getHoursFirstRowLampsStatus(getHours(time))).append(NEW_LINE);
            sb.append(getHoursSecondRowLampsStatus(getHours(time))).append(NEW_LINE);
            sb.append(getMinutesFirstRowLampsStatus(getMinutes(time))).append(NEW_LINE);
            sb.append(getMinutesSecondRowLampsStatus(getMinutes(time)));
            return sb.toString();
        } else {
            return INVALID_TIME_FORMAT;
        }
    }


    public char getTopLampStatus(int seconds){
        if(seconds % 2 == 0){
            return YELLOW;
        } else {
            return OFF;
        }
    }

    public String getHoursFirstRowLampsStatus(int hours){
        int noOfLampsOn = formula1(hours);
        return fillLights(ALL_FOUR_RED_LAMPS_ON , ALL_FOUR_LAMPS_OFF, noOfLampsOn);
    }

    public String getHoursSecondRowLampsStatus(int hours){
        int noOfLampsOn = formula2(hours);
        return fillLights(ALL_FOUR_RED_LAMPS_ON , ALL_FOUR_LAMPS_OFF, noOfLampsOn);
    }

    public String getMinutesFirstRowLampsStatus(int hours){
        int noOfLampsOn = formula1(hours);
        return fillLights(ALL_ELEVEN_LAMPS_ON, ALL_ELEVEN_LAMPS_OFF, noOfLampsOn);
    }

    public String getMinutesSecondRowLampsStatus(int hours){
        int noOfLampsOn = formula2(hours);
        return fillLights(ALL_FOUR_YELLOW_LAMPS_ON, ALL_FOUR_LAMPS_OFF, noOfLampsOn);
    }

    private int formula1(int hours){
        return (int) Math.floor(hours/5);
    }

    private int formula2(int hours){
         return hours % 5;
    }

    private String fillLights(String allOnStatus, String allOffStatus, int noOfLampsOn){
        if(noOfLampsOn == 0){
            return allOffStatus;
        } else if (noOfLampsOn == allOnStatus.length()){
            return allOnStatus;
        }
        String lampStatus = allOnStatus.substring(0,noOfLampsOn).
                concat(allOffStatus.substring((noOfLampsOn),allOffStatus.length()));
        return lampStatus;
    }
}


