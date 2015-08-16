package com.mods.kina.KinaCore.misclib.utils.math;

public class UtilMath{
    public static int cycleNumber(int now, int min, int max){
        if(max < ++now){
            now = min;
        }
        return now;
    }

    public static int cycleNumber(int now, int max){
        return cycleNumber(now, 0, max);
    }
}
