package com.github.nikartm.support

/**
 * @author Ivan V on 07.04.2018.
 * @version 2.0
 */
internal data class StripedDrawable(
    /**
     * Get width of stripes
     */
    var stripeWidth: Float = 0f,

    /**
     * Get drawable background color
     */
    var colorBack: Int = 0,

    /**
     * Get color the main stripe
     */
    var colorMain: Int = 0,

    /**
     * Get color the sub stripe
     */
    var colorSub: Int = 0,

    /**
     * Get alpha stripes
     */
    var stripeAlpha: Float = 0f,

    /**
     * Get drawable corner radius
     */
    var cornerRadius: Float = 0f,

    /**
     * Get duration of stripes animation
     */
    var stripeDuration: Int = 0,

    /**
     * Get tilt of stripes
     */
    var tilt: Float = 0f,

    /**
     * Get state of tilt stripes. If true - tilt to left, false - tilt to right.
     */
    var isStripeRevert: Boolean = false,

    /**
     * Get states of showing stripes
     */
    var isShowStripes: Boolean = false,

    /**
     * Get states of stripes appearance
     */
    var isStripeGradient: Boolean = false,

    /**
     * Get text when button has loading state
     * @return loading text
     */
    var loadingText: String? = null
)