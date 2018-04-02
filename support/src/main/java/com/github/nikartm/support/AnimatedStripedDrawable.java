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
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.nikartm.support.constant.Constants;

/**
 * @author Ivan V on 30.03.2018.
 * @version 1.0
 */
public class AnimatedStripedDrawable extends Drawable implements Animatable {

    private float stripeWidth;
    private int colorBack;
    private int colorMain;
    private int colorSub;
    private float alpha;
    private float cornerRadius;
    private int duration;
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
        if (!running) {
            startAnimation();
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

    private void startAnimation() {
        if (animator == null && !running) {
            animator = ValueAnimator.ofInt(0, 1);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(duration);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    shiftColor(colorMain, colorSub);
                    invalidateSelf();
                }
            });
            start();
        }
    }

    private void shiftColor(int mainColor, int subColor) {
        colorMain = subColor;
        colorSub = mainColor;
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        running = true;
        animator.start();
        setShowStripes(true);
        invalidateSelf();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        running = false;
        animator.cancel();
        setShowStripes(false);
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    public float getStripeWidth() {
        return stripeWidth;
    }

    public AnimatedStripedDrawable setStripeWidth(float stripeWidth) {
        this.stripeWidth = stripeWidth;
        invalidateSelf();
        return this;
    }

    public int getColorBack() {
        return colorBack;
    }

    public AnimatedStripedDrawable setColorBack(int colorBack) {
        this.colorBack = colorBack;
        invalidateSelf();
        return this;
    }

    public int getColorMain() {
        return colorMain;
    }

    public AnimatedStripedDrawable setColorMain(int colorMain) {
        this.colorMain = colorMain;
        invalidateSelf();
        return this;
    }

    public int getColorSub() {
        return colorSub;
    }

    public AnimatedStripedDrawable setColorSub(int colorSub) {
        this.colorSub = colorSub;
        invalidateSelf();
        return this;
    }

    public float getStripeAlpha() {
        return alpha;
    }

    public AnimatedStripedDrawable setStripeAlpha(float alpha) {
        this.alpha = alpha;
        invalidateSelf();
        return this;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public AnimatedStripedDrawable setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidateSelf();
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AnimatedStripedDrawable setDuration(int duration) {
        this.duration = duration;
        invalidateSelf();
        return this;
    }

    public float getTilt() {
        return tilt;
    }

    public AnimatedStripedDrawable setTilt(float tilt) {
        this.tilt = tilt;
        invalidateSelf();
        return this;
    }

    public boolean isStripeRevert() {
        return stripeRevert;
    }

    public AnimatedStripedDrawable setStripeRevert(boolean stripeRevert) {
        this.stripeRevert = stripeRevert;
        invalidateSelf();
        return this;
    }

    public boolean isShowStripes() {
        return showStripes;
    }

    public AnimatedStripedDrawable setShowStripes(boolean showStripes) {
        this.showStripes = showStripes;
        invalidateSelf();
        return this;
    }

    public boolean isStripeGradient() {
        return stripeGradient;
    }

    public AnimatedStripedDrawable setStripeGradient(boolean stripeGradient) {
        this.stripeGradient = stripeGradient;
        invalidateSelf();
        return this;
    }
}