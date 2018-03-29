package com.github.nikartm.support.model;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StrippedButton {

    private float stripeWidth;
    private float stripeAlpha;
    private float mainStripeAlpha;
    private float subStripeAlpha;
    private int stripeDegree;
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

    public float getStripeAlpha() {
        return stripeAlpha;
    }

    public StrippedButton setStripeAlpha(float stripeAlpha) {
        this.stripeAlpha = stripeAlpha;
        return this;
    }

    public float getMainStripeAlpha() {
        return mainStripeAlpha;
    }

    public StrippedButton setMainStripeAlpha(float mainStripeAlpha) {
        this.mainStripeAlpha = mainStripeAlpha;
        return this;
    }

    public float getSubStripeAlpha() {
        return subStripeAlpha;
    }

    public StrippedButton setSubStripeAlpha(float subStripeAlpha) {
        this.subStripeAlpha = subStripeAlpha;
        return this;
    }

    public int getStripeDegree() {
        return stripeDegree;
    }

    public StrippedButton setStripeDegree(int stripeDegree) {
        this.stripeDegree = stripeDegree;
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
