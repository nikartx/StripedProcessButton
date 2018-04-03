package com.github.nikartm.support;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.github.nikartm.support.constant.Constants;

/**
 * Striped process button
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class StripedProcessButton extends AppCompatButton implements Animatable {

    private AnimatedStripedDrawable stripedDrawable;

    private long startAnimDuration = Constants.NO_INIT;
    private long stopAnimDuration = Constants.NO_INIT;
    private boolean buttonAnimation = Constants.DEF_BUTTON_ANIM;
    private String defaultText;
    private String loadingText;

    public StripedProcessButton(Context context) {
        super(context);
        initAttrs(null);
    }

    public StripedProcessButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public StripedProcessButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        AttributeController attrController = new AttributeController(getContext(), attrs);
        stripedDrawable = attrController.getStripedDrawable();
        loadingText = attrController.getLoadingText();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        defaultText = getText() != null ? getText().toString() : "";
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
        animateButton(isRunning());
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        setEnabled(isRunning());
        stripedDrawable.stop();
        animateButton(isRunning());
    }

    @Override
    public boolean isRunning() {
        return stripedDrawable.isRunning();
    }

    private void animateButton(boolean start) {
        if (isButtonAnimation()) {
            long duration;
            if (start) {
                duration = startAnimDuration == Constants.NO_INIT
                        ? Constants.DEF_START_ANIM_DURATION
                        : startAnimDuration;
            } else {
                duration = stopAnimDuration == Constants.NO_INIT
                        ? Constants.DEF_STOP_ANIM_DURATION
                        : stopAnimDuration;
            }
            setCurrentText(start);
            Util.Animation.animateView(this, start, duration);
        }
    }

    private void setCurrentText(boolean start) {
        String currentText;
        if (start) {
            currentText = loadingText == null ? defaultText : loadingText;
        } else {
            currentText = defaultText;
        }
        setText(currentText);
    }

    public AnimatedStripedDrawable adjustButton() {
        return stripedDrawable;
    }

    public long getStartAnimDuration() {
        return startAnimDuration;
    }

    public StripedProcessButton setStartAnimDuration(long startAnimDuration) {
        this.startAnimDuration = startAnimDuration;
        invalidate();
        return this;
    }

    public long getStopAnimDuration() {
        return stopAnimDuration;
    }

    public StripedProcessButton setStopAnimDuration(long stopAnimDuration) {
        this.stopAnimDuration = stopAnimDuration;
        invalidate();
        return this;
    }

    public String getLoadingText() {
        return loadingText;
    }

    public StripedProcessButton setLoadingText(String loadingText) {
        this.loadingText = loadingText;
        invalidate();
        return this;
    }

    public boolean isButtonAnimation() {
        return buttonAnimation;
    }

    public StripedProcessButton setButtonAnimation(boolean buttonAnimation) {
        this.buttonAnimation = buttonAnimation;
        invalidate();
        return this;
    }

}
