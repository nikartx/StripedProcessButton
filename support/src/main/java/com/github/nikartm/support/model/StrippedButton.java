package com.github.nikartm.support.model;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StrippedButton {

    private float stripeWidth;
    private int stripeAlpha;
    private int mainStripeAlpha;
    private int subStripeAlpha;
    private int stripeAngle;
    private long duration;
    private int background;
    private int mainStripeColor;
    private int subStripeColor;
    private boolean active;

    public StrippedButton() {
    }

    public float getStripeWidth() {
        return stripeWidth;
    }

    public StrippedButton setStripeWidth(float stripeWidth) {
        this.stripeWidth = stripeWidth;
        return this;
    }

    public int getStripeAlpha() {
        return stripeAlpha;
    }

    public StrippedButton setStripeAlpha(int stripeAlpha) {
        this.stripeAlpha = stripeAlpha;
        return this;
    }

    public int getMainStripeAlpha() {
        return mainStripeAlpha;
    }

    public StrippedButton setMainStripeAlpha(int mainStripeAlpha) {
        this.mainStripeAlpha = mainStripeAlpha;
        return this;
    }

    public int getSubStripeAlpha() {
        return subStripeAlpha;
    }

    public StrippedButton setSubStripeAlpha(int subStripeAlpha) {
        this.subStripeAlpha = subStripeAlpha;
        return this;
    }

    public int getStripeAngle() {
        return stripeAngle;
    }

    public StrippedButton setStripeAngle(int stripeAngle) {
        this.stripeAngle = stripeAngle;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public StrippedButton setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public int getBackground() {
        return background;
    }

    public StrippedButton setBackground(int background) {
        this.background = background;
        return this;
    }

    public int getMainStripeColor() {
        return mainStripeColor;
    }

    public StrippedButton setMainStripeColor(int mainStripeColor) {
        this.mainStripeColor = mainStripeColor;
        return this;
    }

    public int getSubStripeColor() {
        return subStripeColor;
    }

    public StrippedButton setSubStripeColor(int subStripeColor) {
        this.subStripeColor = subStripeColor;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public StrippedButton setActive(boolean active) {
        this.active = active;
        return this;
    }
}
