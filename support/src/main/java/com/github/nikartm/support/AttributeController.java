package com.github.nikartm.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.nikartm.support.constant.Constants;
import com.github.nikartm.support.model.StrippedButton;

/**
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
public class AttributeController {

    private StrippedButton button;

    public AttributeController(Context context, AttributeSet attrs) {
        button = new StrippedButton();
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StripedProgressButton);
        try {
            float stripeWidth = typedArray.getDimension(R.styleable.StripedProgressButton_spb_stripeWidth, Constants.STRIPE_WIDTH);
            float stripeAlpha = typedArray.getFloat(R.styleable.StripedProgressButton_spb_stripeAlpha, Constants.STRIPE_ALPHA);
            float mainStripeAlpha = typedArray.getFloat(R.styleable.StripedProgressButton_spb_mainStripeAlpha, stripeAlpha);
            float subStripeAlpha = typedArray.getFloat(R.styleable.StripedProgressButton_spb_subStripeAlpha, stripeAlpha);
            int stripeDegree = typedArray.getInt(R.styleable.StripedProgressButton_spb_degree, Constants.STRIPE_DEGREE);
            int duration = typedArray.getInt(R.styleable.StripedProgressButton_spb_duration, Constants.DURATION);
            int background = typedArray.getColor(R.styleable.StripedProgressButton_spb_background, Constants.DEF_BACKGROUND);
            int mainStripeColor = typedArray.getColor(R.styleable.StripedProgressButton_spb_mainStripColor, Constants.DEF_MAIN_STRIPE);
            int subStripeColor = typedArray.getColor(R.styleable.StripedProgressButton_spb_subStripeColor, Constants.DEF_SUB_STRIPE);
            boolean active = typedArray.getBoolean(R.styleable.StripedProgressButton_spb_active, Constants.ACTIVE);

            button.setStripeWidth(stripeWidth)
                    .setStripeAlpha(Util.computeAlpha(stripeAlpha))
                    .setMainStripeAlpha(Util.computeAlpha(mainStripeAlpha))
                    .setSubStripeAlpha(Util.computeAlpha(subStripeAlpha))
                    .setStripeDegree(stripeDegree)
                    .setDuration(duration)
                    .setBackground(background)
                    .setMainStripeColor(mainStripeColor)
                    .setSubStripeColor(subStripeColor)
                    .setActive(active);
        } finally {
            typedArray.recycle();
        }
    }

    public StrippedButton getButton() {
        return button;
    }

}
