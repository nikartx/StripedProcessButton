package com.github.nikartm.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.nikartm.support.constant.Constants;

/**
 * Controller attrs of striped process button
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
class AttributeController {

    private StripedDrawable stripedDrawable;

    public AttributeController(Context context, AttributeSet attrs) {
        stripedDrawable = new StripedDrawable();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StripedProcessButton);
        try {
            float stripeWidth = typedArray.getDimension(R.styleable.StripedProcessButton_spb_stripeWidth, Constants.STRIPE_WIDTH);
            float stripeAlpha = typedArray.getFloat(R.styleable.StripedProcessButton_spb_stripeAlpha, Constants.STRIPE_ALPHA);
            int stripeTilt = typedArray.getInt(R.styleable.StripedProcessButton_spb_stripeTilt, Constants.STRIPE_TILT);
            int stripeDuration = typedArray.getInt(R.styleable.StripedProcessButton_spb_stripeDuration, Constants.DURATION);
            int background = typedArray.getColor(R.styleable.StripedProcessButton_spb_background, Constants.DEF_BACKGROUND);
            int mainStripeColor = typedArray.getColor(R.styleable.StripedProcessButton_spb_mainStripColor, Constants.DEF_MAIN_STRIPE);
            int subStripeColor = typedArray.getColor(R.styleable.StripedProcessButton_spb_subStripeColor, Constants.DEF_SUB_STRIPE);
            float cornerRadius = typedArray.getFloat(R.styleable.StripedProcessButton_spb_cornerRadius, Constants.CORNER);
            boolean stripeRevert = typedArray.getBoolean(R.styleable.StripedProcessButton_spb_stripeRevert, Constants.REVERT);
            boolean showStripes = typedArray.getBoolean(R.styleable.StripedProcessButton_spb_showStripes, Constants.SHOW_STRIPES);
            boolean stripeGradient = typedArray.getBoolean(R.styleable.StripedProcessButton_spb_stripeGradient, Constants.GRADIENT);
            String loadingText = typedArray.getString(R.styleable.StripedProcessButton_spb_loadingText);

            stripedDrawable.setStripeWidth(stripeWidth)
                    .setStripeAlpha(stripeAlpha)
                    .setTilt(stripeTilt)
                    .setStripeDuration(stripeDuration)
                    .setColorBack(background)
                    .setColorMain(mainStripeColor)
                    .setColorSub(subStripeColor)
                    .setCornerRadius(cornerRadius)
                    .setStripeRevert(stripeRevert)
                    .setShowStripes(showStripes)
                    .setStripeGradient(stripeGradient)
                    .setLoadingText(loadingText);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Get {@link StripedDrawable} when attrs initialized
     * @return initialized {@link StripedDrawable}
     */
    public StripedDrawable getStripedDrawable() {
        return stripedDrawable;
    }

}
