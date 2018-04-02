package com.github.nikartm.support.model;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StrippedButton {

    private float stripeWidth;
    private float stripeAlpha;
    private int stripeTilt;
    private long duration;
    private int background;
    private int mainStripeColor;
    private int subStripeColor;
    private float cornerRadius;
    private boolean stripeRevert;
    private boolean showStripes;
    private boolean active;

    public StrippedButton() {}

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

    public int getStripeTilt() {
        return stripeTilt;
    }

    public StrippedButton setStripeTilt(int stripeTilt) {
        this.stripeTilt = stripeTilt;
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

    public float getCornerRadius() {
        return cornerRadius;
    }

    public StrippedButton setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    public boolean isStripeRevert() {
        return stripeRevert;
    }

    public StrippedButton setStripeRevert(boolean stripeRevert) {
        this.stripeRevert = stripeRevert;
        return this;
    }

    public boolean isShowStripes() {
        return showStripes;
    }

    public StrippedButton setShowStripes(boolean showStripes) {
        this.showStripes = showStripes;
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
