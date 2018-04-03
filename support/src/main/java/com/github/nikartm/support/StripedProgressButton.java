package com.github.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StripedProgressButton extends AppCompatButton implements Animatable {

    private AnimatedStripedDrawable stripedDrawable;

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
        AttributeController attrController = new AttributeController(getContext(), attrs);
        stripedDrawable = attrController.getStripedDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackground(stripedDrawable);
        setEnabled(!isRunning());
        super.onDraw(canvas);
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        setEnabled(isRunning());
        stripedDrawable.start();
        Util.Animation.animateView(this, true, 800);
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        setEnabled(isRunning());
        stripedDrawable.stop();
        Util.Animation.animateView(this, false, 300);
    }

    @Override
    public boolean isRunning() {
        return stripedDrawable.isRunning();
    }

    public AnimatedStripedDrawable adjustButton() {
        return stripedDrawable;
    }
}
