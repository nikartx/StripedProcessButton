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

import com.github.nikartm.support.model.StrippedButton;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StripedProgressButton extends AppCompatButton implements Animatable {

    private float stripeWidth;
    private float stripeEdge = 5f;
    private float startY;
    private float stopY;

    private StrippedButton button;
    private AttributeController attrController;
    private Paint paint;
    private ValueAnimator btnAnimator;
    private State running = State.STOPPED;

    public StripedProgressButton(Context context) {
        super(context);
        initAttrs(null);
    }

    public StripedProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public StripedProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        attrController = new AttributeController(getContext(), attrs);
        button = attrController.getButton();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        stripedWidthToDp();
    }

    private void stripedWidthToDp() {
        float density = getContext().getResources().getDisplayMetrics().density;
        stripeWidth = button.getStripeWidth() * density;
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
        int stopX = button.getStripeDegree() / -1;
        canvas.drawColor(button.getBackground());
        do {
            paint.setColor(button.getMainStripeColor());
            paint.setAlpha(button.getMainStripeAlpha());
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;

            paint.setColor(button.getSubStripeColor());
            paint.setAlpha(button.getSubStripeAlpha());
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;
        } while (startX < getWidth() + button.getStripeDegree());
    }

    private void startAnimation() {
        if (btnAnimator == null && running == State.STOPPED) {
            btnAnimator = ValueAnimator.ofInt(0, 1);
            btnAnimator.setRepeatCount(ValueAnimator.INFINITE);
            btnAnimator.setDuration(button.getDuration());
            btnAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    shiftColor(button.getMainStripeColor(), button.getSubStripeColor());
                    postInvalidate();
                }
            });
            start();
        }
    }

    private void shiftColor(int mainColor, int subColor) {
        button.setMainStripeColor(subColor);
        button.setSubStripeColor(mainColor);
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
