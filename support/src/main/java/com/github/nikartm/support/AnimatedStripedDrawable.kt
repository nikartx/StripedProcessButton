package com.github.nikartm.support;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Animated striped drawable
 * @author Ivan V on 30.03.2018.
 * @version 1.0
 */
public class AnimatedStripedDrawable extends Drawable {

    private int viewHeight;
    private int viewWidth;
    private float tiltLeft = 0f;
    private float tiltRight = 0f;
    private boolean running = false;

    private StripedDrawable drawable;
    private ValueAnimator animator;
    private Shader stripesShader;

    public AnimatedStripedDrawable(StripedDrawable drawable) {
        this.drawable = drawable;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        viewHeight = bounds.height();
        viewWidth = bounds.width();
        adjustStripes();
    }

    private void adjustStripes() {
        if (!drawable.isStripeRevert()) {
            tiltLeft = drawable.getTilt();
            tiltRight = 0;
        } else {
            tiltRight = drawable.getTilt();
            tiltLeft = 0;
        }
    }

    private void initAnimator() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 1);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(drawable.getStripeDuration());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    shiftColor(drawable.getColorMain(), drawable.getColorSub());
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
            drawable.setShowStripes(false);
        }
    }

    private void drawStripes(Canvas canvas) {
        final Paint paintBack = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Paint paintStripes = new Paint(Paint.ANTI_ALIAS_FLAG);
        final Rect rect = new Rect(0, 0, viewWidth, viewHeight);
        final RectF rectF = new RectF(rect);
        final int stripesAlpha = Util.computeAlpha(drawable.getStripeAlpha());

        if (drawable.isStripeGradient()) {
            stripesShader = createGradientShader();
        } else {
            stripesShader = createShader();
        }

        paintBack.setColor(drawable.getColorBack());
        float cornerRadius = drawable.getCornerRadius();
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBack);

        if (drawable.isShowStripes()) {
            paintStripes.setAlpha(stripesAlpha);
            paintStripes.setShader(stripesShader);
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintStripes);
        }
    }

    @NonNull
    private LinearGradient createShader() {
        return new LinearGradient(drawable.getStripeWidth(), tiltLeft, 0, tiltRight,
                new int[] { drawable.getColorMain(), drawable.getColorSub() }, new float[] { .5f, .5f },
                Shader.TileMode.REPEAT);
    }

    @NonNull
    private LinearGradient createGradientShader() {
        return new LinearGradient(drawable.getStripeWidth(), tiltLeft, 0, tiltRight,
                drawable.getColorMain(), drawable.getColorSub(), Shader.TileMode.REPEAT);
    }

    private void shiftColor(int mainColor, int subColor) {
        drawable.setColorMain(subColor);
        drawable.setColorSub(mainColor);
    }

    // Start stripes animation
    protected void start() {
        if (isRunning()) {
            return;
        }
        running = true;
        initAnimator();
        animator.start();
        drawable.setShowStripes(true);
        invalidateSelf();
    }

    // Stop stripes animation
    protected void stop() {
        if (!isRunning()) {
            return;
        }
        running = false;
        animator.cancel();
        drawable.setShowStripes(false);
        invalidateSelf();
    }

    // Check if stripes animation running
    protected boolean isRunning() {
        return animator != null && animator.isStarted();
    }

}