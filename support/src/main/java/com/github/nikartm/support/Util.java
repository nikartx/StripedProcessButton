package com.github.nikartm.support;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
class Util {

    private static final int MAX_ALPHA = 255;
    private static final int MIN_ALPHA = 0;

    public static int computeAlpha(float value) {
        int result;
        if (valueAlpha(value) >= MAX_ALPHA) {
            result = MAX_ALPHA;
        } else if (Float.floatToIntBits(value) < MIN_ALPHA) {
            result = MIN_ALPHA;
        } else {
            result = (int) valueAlpha(value);
        }
        return result;
    }

    private static float valueAlpha(float value) {
        return MAX_ALPHA * (value * 100f) / 100f;
    }
}
