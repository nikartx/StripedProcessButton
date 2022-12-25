package com.github.nikartm.support

import android.view.View
import android.view.ViewAnimationUtils

/**
 * @author Ivan V on 29.03.2018.
 * @version 2.0
 */
internal object Util {

    private const val MAX_PERCENT = 100f
    private const val MAX_ALPHA = 255
    private const val MIN_ALPHA = 0

    fun computeAlpha(value: Float): Int {
        return if (compute(value) >= MAX_ALPHA) {
                MAX_ALPHA
            } else if (value < MIN_ALPHA) {
                MIN_ALPHA
            } else {
                compute(value).toInt()
            }
    }

    private fun compute(value: Float): Float {
        return MAX_ALPHA * (value * MAX_PERCENT) / MAX_PERCENT
    }

    /**
     * Animation for the striped process button
     */
    internal object Animation {
        @JvmStatic
        fun animateView(view: View, start: Boolean, duration: Long) {
            val cX = if (start) view.width / 2 else 0
            val cY = view.height / 2
            val finalRadius = Math.max(view.width, view.height)
            ViewAnimationUtils
                .createCircularReveal(view, cX, cY, 0f, finalRadius.toFloat())
                .apply {
                    this.duration = duration
                    start()
                }
        }
    }
}
