package com.github.nikartm.support;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Animated striped drawable
 * @author Ivan V on 30.03.2018.
 * @version 1.0
 */
public class AnimatedStripedDrawable extends Drawable {

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

    private float density;
    private int viewHeight;
    private int viewWidth;
    private float tiltLeft = 0f;
    private float tiltRight = 0f;
    private boolean running = false;

    private Context context;
    private ValueAnimator animator;
    private Shader stripesShader;

    public AnimatedStripedDrawable(Context context) {
        this.context = context;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        density = context.getResources().getDisplayMetrics().density;
        viewHeight = bounds.height();
        viewWidth = bounds.width();
        adjustStripes();
        initAnimator();
    }

    private void adjustStripes() {
        stripeWidth = stripeWidth * density;
        if (!stripeRevert) {
            tiltLeft = tilt / density;
            tiltRight = 0;
        } else {
            tiltRight = tilt / density;
            tiltLeft = 0;
        }
    }

    private void initAnimator() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 1);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(stripeDuration);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    shiftColor(colorMain, colorSub);
                    invalidateSelf();
                }
            });
        }
    }

    @Override
    public int getIntrinsicWidth() {
        return viewWidth > 0 ? viewWidth : super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return viewHeight > 0 ? viewHeight : super.getIntrinsicHeight();
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {}

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        drawStripes(canvas);
        startStripesAnimation();
    }

    private void startStripesAnimation() {
        if (running) {
            start();
        } else {
            setShowStripes(false);
        }
    }

    private void drawStripes(Canvas canvas) {
        final Paint paintBack = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Paint paintStripes = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Rect rect = new Rect(0, 0, viewWidth, viewHeight);
        final RectF rectF = new RectF(rect);
        final int stripesAlpha = Util.computeAlpha(alpha);

        if (stripeGradient) {
            stripesShader = createGradientShader();
        } else {
            stripesShader = createShader();
        }

        paintBack.setColor(colorBack);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBack);

        if (showStripes) {
            paintStripes.setAlpha(stripesAlpha);
            paintStripes.setShader(stripesShader);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintStripes);
        }
    }

    @NonNull
    private LinearGradient createShader() {
        return new LinearGradient(stripeWidth, tiltLeft, 0, tiltRight,
                new int[] { colorMain, colorSub }, new float[] { .5f, .5f },
                Shader.TileMode.REPEAT);
    }

    @NonNull
    private LinearGradient createGradientShader() {
        return new LinearGradient(stripeWidth, tiltLeft, 0, tiltRight,
                colorMain, colorSub, Shader.TileMode.REPEAT);
    }

    private void shiftColor(int mainColor, int subColor) {
        colorMain = subColor;
        colorSub = mainColor;
    }

    // Start stripes animation
    protected void start() {
        if (isRunning()) {
            return;
        }
        running = true;
        initAnimator();
        animator.start();
        setShowStripes(true);
        invalidateSelf();
    }

    // Stop stripes animation
    protected void stop() {
        if (!isRunning()) {
            return;
        }
        running = false;
        animator.cancel();
        setShowStripes(false);
        invalidateSelf();
    }

    // Check if stripes animation running
    protected boolean isRunning() {
        return animator != null && animator.isStarted();
    }

    /**
     * Get striped width
     */
    public float getStripeWidth() {
        return stripeWidth;
    }

    /**
     * Set striped width
     * @param stripeWidth drawable striped width
     */
    public AnimatedStripedDrawable setStripeWidth(float stripeWidth) {
        this.stripeWidth = stripeWidth;
        invalidateSelf();
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
    public AnimatedStripedDrawable setColorBack(int colorBack) {
        this.colorBack = colorBack;
        invalidateSelf();
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
    public AnimatedStripedDrawable setColorMain(int colorMain) {
        this.colorMain = colorMain;
        invalidateSelf();
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
    public AnimatedStripedDrawable setColorSub(int colorSub) {
        this.colorSub = colorSub;
        invalidateSelf();
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
    public AnimatedStripedDrawable setStripeAlpha(float alpha) {
        this.alpha = alpha;
        invalidateSelf();
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
    public AnimatedStripedDrawable setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidateSelf();
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
    public AnimatedStripedDrawable setStripeDuration(int stripeDuration) {
        this.stripeDuration = stripeDuration;
        invalidateSelf();
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
    public AnimatedStripedDrawable setTilt(float tilt) {
        this.tilt = tilt;
        invalidateSelf();
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
    public AnimatedStripedDrawable setStripeRevert(boolean stripeRevert) {
        this.stripeRevert = stripeRevert;
        invalidateSelf();
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
    public AnimatedStripedDrawable setShowStripes(boolean showStripes) {
        this.showStripes = showStripes;
        invalidateSelf();
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
    public AnimatedStripedDrawable setStripeGradient(boolean stripeGradient) {
        this.stripeGradient = stripeGradient;
        invalidateSelf();
        return this;
    }
}