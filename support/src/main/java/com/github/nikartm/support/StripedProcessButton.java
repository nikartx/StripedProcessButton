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
    private State state = State.STOP;

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
        launchAnimationWithDelay();
    }

    // Launch animation with delay when view attached to window
    private void launchAnimationWithDelay() {
        switch (state) {
            case START:
                start();
                break;
            case STOP:
                stop();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackground(stripedDrawable);
        setEnabled(!isRunning());
        super.onDraw(canvas);
    }

    @Override
    public void start() {
        state = State.START;
        if (isRunning() || !isAttachedToWindow()) {
            return;
        }
        setEnabled(isRunning());
        stripedDrawable.start();
        animateButton(isRunning());
    }

    @Override
    public void stop() {
        state = State.STOP;
        if (!isRunning() || !isAttachedToWindow()) {
            return;
        }
        setEnabled(isRunning());
        stripedDrawable.stop();
        animateButton(isRunning());
    }

    @Override
    public boolean isRunning() {
        return isAttachedToWindow() && stripedDrawable.isRunning();
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

    /**
     * Adjust the striped drawable with animation
     * @return striped drawable
     */
    public AnimatedStripedDrawable adjustButton() {
        return stripedDrawable;
    }

    /**
     * Get current start animation duration
     * @return start duration in ms
     */
    public long getStartAnimDuration() {
        return startAnimDuration;
    }

    /**
     * Set start animation duration in ms
     */
    public StripedProcessButton setStartAnimDuration(long startAnimDuration) {
        this.startAnimDuration = startAnimDuration;
        invalidate();
        return this;
    }

    /**
     * Get current stop animation duration
     * @return stop duration in ms
     */
    public long getStopAnimDuration() {
        return stopAnimDuration;
    }

    /**
     * Set stop animation duration in ms
     */
    public StripedProcessButton setStopAnimDuration(long stopAnimDuration) {
        this.stopAnimDuration = stopAnimDuration;
        invalidate();
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
    public StripedProcessButton setLoadingText(String loadingText) {
        this.loadingText = loadingText;
        invalidate();
        return this;
    }

    /**
     * Get button stripe animation state
     * @return true if button can be animated
     */
    public boolean isButtonAnimation() {
        return buttonAnimation;
    }

    /**
     * Set button stripe animation state
     * @param buttonAnimation if tru button can be animated
     */
    public StripedProcessButton setButtonAnimation(boolean buttonAnimation) {
        this.buttonAnimation = buttonAnimation;
        invalidate();
        return this;
    }

    /**
     * State of launch methods with delay
     */
    private enum State {
        START, STOP
    }

}
