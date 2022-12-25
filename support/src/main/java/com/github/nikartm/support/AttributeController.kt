package com.github.nikartm.support

import android.content.Context
import android.util.AttributeSet
import com.github.nikartm.support.constant.Constants

/**
 * Controller attrs of striped process button
 * @author Ivan V on 29.03.2018.
 * @version 2.0
 */
internal class AttributeController(private val context: Context, private val attrs: AttributeSet?) {

    /**
     * Get [StripedDrawable] when attrs initialized
     * @return initialized [StripedDrawable]
     */
    var stripedDrawable: StripedDrawable = StripedDrawable()
        private set

    init {
        initAttrs()
    }

    private fun initAttrs() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StripedProcessButton)
        try {
            val stripeWidth = typedArray.getDimension(
                R.styleable.StripedProcessButton_spb_stripeWidth,
                Constants.STRIPE_WIDTH
            )
            val stripeAlpha = typedArray.getFloat(
                R.styleable.StripedProcessButton_spb_stripeAlpha,
                Constants.STRIPE_ALPHA
            )
            val stripeTilt = typedArray.getInt(
                R.styleable.StripedProcessButton_spb_stripeTilt,
                Constants.STRIPE_TILT
            )
            val stripeDuration = typedArray.getInt(
                R.styleable.StripedProcessButton_spb_stripeDuration,
                Constants.DURATION
            )
            val background = typedArray.getColor(
                R.styleable.StripedProcessButton_spb_background,
                Constants.DEF_BACKGROUND
            )
            val mainStripeColor = typedArray.getColor(
                R.styleable.StripedProcessButton_spb_mainStripColor,
                Constants.DEF_MAIN_STRIPE
            )
            val subStripeColor = typedArray.getColor(
                R.styleable.StripedProcessButton_spb_subStripeColor,
                Constants.DEF_SUB_STRIPE
            )
            val cornerRadius = typedArray.getFloat(
                R.styleable.StripedProcessButton_spb_cornerRadius,
                Constants.CORNER.toFloat()
            )
            val stripeRevert = typedArray.getBoolean(
                R.styleable.StripedProcessButton_spb_stripeRevert,
                Constants.REVERT
            )
            val showStripes = typedArray.getBoolean(
                R.styleable.StripedProcessButton_spb_showStripes,
                Constants.SHOW_STRIPES
            )
            val stripeGradient = typedArray.getBoolean(
                R.styleable.StripedProcessButton_spb_stripeGradient,
                Constants.GRADIENT
            )
            val loadingText = typedArray.getString(R.styleable.StripedProcessButton_spb_loadingText)

            // Apply drawable state
            stripedDrawable = stripedDrawable.copy(
                stripeWidth = stripeWidth,
                stripeAlpha = stripeAlpha,
                tilt = stripeTilt.toFloat(),
                stripeDuration = stripeDuration,
                colorBack = background,
                colorMain = mainStripeColor,
                colorSub = subStripeColor,
                cornerRadius = cornerRadius,
                isStripeRevert = stripeRevert,
                isShowStripes = showStripes,
                isStripeGradient = stripeGradient,
                loadingText = loadingText
            )
        } finally {
            typedArray.recycle()
        }
    }
}
