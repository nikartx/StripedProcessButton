package com.github.nikartm.support.constant

import android.graphics.Color

/**
 * Constants of stripped process button
 * @author Ivan V on 29.03.2018.
 * @version 2.0
 */
object Constants {
    const val STRIPE_WIDTH = 36f
    const val STRIPE_ALPHA = 0.3f
    const val CORNER = 0
    const val STRIPE_TILT = 25
    const val DURATION = 250
    const val REVERT = false
    const val SHOW_STRIPES = false
    const val GRADIENT = true
    const val NO_INIT = -1L
    const val DEF_START_ANIM_DURATION: Long = 700
    const val DEF_STOP_ANIM_DURATION: Long = 300

    @JvmField
    val DEF_BACKGROUND = Color.parseColor("#4CAF50")
    @JvmField
    val DEF_MAIN_STRIPE = Color.parseColor("#4CAF50")
    @JvmField
    val DEF_SUB_STRIPE = Color.parseColor("#CFD8DC")
}
