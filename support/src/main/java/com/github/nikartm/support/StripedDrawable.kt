package com.github.nikartm.support;

/**
 * @author Ivan V on 07.04.2018.
 * @version 1.0
 */
public class StripedDrawable {

    private float stripeWidth;
    private int colorBack;
    private int colorMain;
    private int colorSub;
    private float alpha;
    private float cornerRadius;
    private int stripeDuration;
    private float tilt;
    private boolean stripeRevert;
    private boolean showStripes;
    private boolean stripeGradient;
    private String loadingText;

    public StripedDrawable() {
    }

    /**
     * Get width of stripes
     */
    public float getStripeWidth() {
        return stripeWidth;
    }

    /**
     * Set width of stripes
     * @param stripeWidth on view
     */
    public StripedDrawable setStripeWidth(float stripeWidth) {
        this.stripeWidth = stripeWidth;
        return this;
    }

    /**
     * Get drawable background color
     */
    public int getColorBack() {
        return colorBack;
    }

    /**
     * Set drawable background color
     * @param colorBack background color
     */
    public StripedDrawable setColorBack(int colorBack) {
        this.colorBack = colorBack;
        return this;
    }

    /**
     * Get color the main stripe
     */
    public int getColorMain() {
        return colorMain;
    }

    /**
     * Set color the main stripe
     * @param colorMain color of main stripe
     */
    public StripedDrawable setColorMain(int colorMain) {
        this.colorMain = colorMain;
        return this;
    }

    /**
     * Get color the sub stripe
     */
    public int getColorSub() {
        return colorSub;
    }

    /**
     * Set color the sub stripe
     * @param colorSub color of sub stripe
     */
    public StripedDrawable setColorSub(int colorSub) {
        this.colorSub = colorSub;
        return this;
    }

    /**
     * Get alpha stripes
     */
    public float getStripeAlpha() {
        return alpha;
    }

    /**
     * Set alpha drawable stripes
     * @param alpha stripes
     */
    public StripedDrawable setStripeAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    /**
     * Get drawable corner radius
     */
    public float getCornerRadius() {
        return cornerRadius;
    }

    /**
     * Set drawable corner radius
     * @param cornerRadius radius
     */
    public StripedDrawable setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        return this;
    }

    /**
     * Get duration of stripes animation
     */
    public int getStripeDuration() {
        return stripeDuration;
    }

    /**
     * Set duration of stripes animation
     */
    public StripedDrawable setStripeDuration(int stripeDuration) {
        this.stripeDuration = stripeDuration;
        return this;
    }

    /**
     * Get tilt of stripes
     */
    public float getTilt() {
        return tilt;
    }

    /**
     * Set tilt of stripes
     * @param tilt of stripes
     */
    public StripedDrawable setTilt(float tilt) {
        this.tilt = tilt;
        return this;
    }

    /**
     * Get state of tilt stripes. If true - tilt to left, false - tilt to right.
     */
    public boolean isStripeRevert() {
        return stripeRevert;
    }

    /**
     * Get state of tilt stripes.
     * @param stripeRevert If true - tilt to left, false - tilt to right.
     */
    public StripedDrawable setStripeRevert(boolean stripeRevert) {
        this.stripeRevert = stripeRevert;
        return this;
    }

    /**
     * Get states of showing stripes
     */
    public boolean isShowStripes() {
        return showStripes;
    }

    /**
     * Set states of showing stripes
     * @param showStripes If true - stripes showing, false - stripes gone.
     */
    public StripedDrawable setShowStripes(boolean showStripes) {
        this.showStripes = showStripes;
        return this;
    }

    /**
     * Get states of stripes appearance
     */
    public boolean isStripeGradient() {
        return stripeGradient;
    }

    /**
     * Set the state of striped appearance of drawable
     * @param stripeGradient if true stripes has gradient style, false - flat strips
     */
    public StripedDrawable setStripeGradient(boolean stripeGradient) {
        this.stripeGradient = stripeGradient;
        return this;
    }

    /**
     * Get text when button has loading state
     * @return loading text
     */
    public String getLoadingText() {
        return loadingText;
    }

    /**
     * Set text when button has loading state
     * @param loadingText text when loading started
     */
    public StripedDrawable setLoadingText(String loadingText) {
        this.loadingText = loadingText;
        return this;
    }
}
