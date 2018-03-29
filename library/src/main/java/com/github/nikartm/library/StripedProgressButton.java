package com.github.nikartm.library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

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

    private int colorBackground = Color.parseColor("#4CAF50");
    private int colorMainStripe = Color.parseColor("#4CAF50");
    private int colorSecondaryStripe = Color.parseColor("#CFD8DC");

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
        super.onDraw(canvas);
    }

    private void drawStripes(Canvas canvas) {
        int startX = 0;
        int stopX = stripeDegree / -1;
        canvas.drawColor(colorBackground);
        do {
            paint.setColor(colorMainStripe);
            paint.setAlpha(stripeAlpha);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;

            paint.setColor(colorSecondaryStripe);
            paint.setAlpha(stripeAlpha);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            startX += stripeWidth + 1;
            stopX += stripeWidth + 1;
        } while (startX < getWidth() + stripeDegree);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    private enum State {
        PROGRESS, DONE, STOPPED
    }

}
