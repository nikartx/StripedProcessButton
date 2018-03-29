package com.github.nikartm.support;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
class Util {

    public static int computeAlpha(float value) {
        int result;
        if (value * 100 > 100) {
            result = 100;
        } else if (Float.floatToIntBits(value) < 0) {
            result = 0;
        } else {
            result = (int) (value * 100);
        }
        return result;
    }
}
