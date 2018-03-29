package com.github.nikartm.support;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.github.nikartm.support.constant.Constants;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StripedProgressButton extends AppCompatButton implements Animatable {

    private float stripeWidth = 7f;
    private int stripeAlpha = 0x90;
    private int stripeDegree = 40;
    private long duration = 150L;
    private float stripeEdge = 5f;
    private float startY;
    private float stopY;
    private float density;

    private int background = Constants.DEF_BACKGROUND;
    private int mainStripe = Constants.DEF_MAIN_STRIPE;
    private int secondaryStripe = Constants.DEF_SEC_STRIPE;

    private Paint paint;
    private ValueAnimator btnAnimator;
    private State running = State.STOPPED;

    public StripedProgressButton(Context context) {
        super(context);
    }

    public StripedProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StripedProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        stripedWidthToDp();
    }

    private void stripedWidthToDp() {
        density = getContext().getResources().getDisplayMetrics().density;
        stripeWidth = stripeWidth * density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(stripeWidth);
            startY = stripeEdge / -1;
            stopY = getHeight() + stripeEdge;
        }
        drawStripes(canvas);
        startAnimation();
        super.onDraw(canvas);
    }

    private void drawStripes(Canvas canvas) {
        int startX = 0;
        int stopX = stripeDegree / -1;
        canvas.drawColor(background);
        do {
            paint.setColor(mainStripe);
            paint.setAlpha(stripeAlpha);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;

            paint.setColor(secondaryStripe);
            paint.setAlpha(stripeAlpha);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;
        } while (startX < getWidth() + stripeDegree);
    }

    private void startAnimation() {
        if (btnAnimator == null && running == State.STOPPED) {
            btnAnimator = ValueAnimator.ofInt(0, 1);
            btnAnimator.setRepeatCount(ValueAnimator.INFINITE);
            btnAnimator.setDuration(duration);
            btnAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    shiftColor(mainStripe, secondaryStripe);
                    postInvalidate();
                }
            });
            start();
        }
    }

    private void shiftColor(int mainColor, int secColor) {
        mainStripe = secColor;
        secondaryStripe = mainColor;
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        running = State.PROGRESS;
        btnAnimator.start();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        running = State.STOPPED;
        btnAnimator.cancel();
    }

    @Override
    public boolean isRunning() {
        return running == State.PROGRESS;
    }

    private enum State {
        PROGRESS, DONE, STOPPED
    }

}
