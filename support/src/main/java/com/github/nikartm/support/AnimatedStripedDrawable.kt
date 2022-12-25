package com.github.nikartm.support

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.animation.doOnRepeat

/**
 * Animated striped drawable
 * @author Ivan V on 30.03.2018.
 * @version 2.0
 */
internal class AnimatedStripedDrawable(private val stripedDrawable: StripedDrawable) : Drawable() {

    private var viewHeight = 0
    private var viewWidth = 0
    private var tiltLeft = 0f
    private var tiltRight = 0f
    private var animator: ValueAnimator? = null
    private var stripesShader: Shader? = null

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        with(bounds) {
            viewHeight = height()
            viewWidth = width()
        }
        adjustStripes()
    }

    private fun adjustStripes() = with(stripedDrawable) {
        tiltLeft = if (!isStripeRevert) tilt else 0f
        tiltRight = if (isStripeRevert) tilt else 0f
    }

    private fun initAnimator() = with(stripedDrawable) {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 1).apply {
                repeatCount = ValueAnimator.INFINITE
                duration = stripeDuration.toLong()
                doOnRepeat {
                    shiftColor(colorMain, colorSub)
                    invalidateSelf()
                }
            }
        }
    }

    override fun getIntrinsicWidth(): Int {
        return if (viewWidth > 0) viewWidth else super.getIntrinsicWidth()
    }

    override fun getIntrinsicHeight(): Int {
        return if (viewHeight > 0) viewHeight else super.getIntrinsicHeight()
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun draw(canvas: Canvas) {
        drawStripes(canvas)
        startStripesAnimation()
    }

    private fun startStripesAnimation() {
        if (isRunning()) start() else stop()
    }

    private fun drawStripes(canvas: Canvas) = with(stripedDrawable) {
        val paintBack = Paint(Paint.ANTI_ALIAS_FLAG)
        val paintStripes = Paint(Paint.ANTI_ALIAS_FLAG)
        val rect = Rect(0, 0, viewWidth, viewHeight)
        val rectF = RectF(rect)
        val stripesAlpha = Util.computeAlpha(stripeAlpha)
        stripesShader = if (isStripeGradient) createGradientShader() else createShader()
        paintBack.color = colorBack
        val cornerRadius = cornerRadius
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintBack)
        if (isShowStripes) {
            paintStripes.alpha = stripesAlpha
            paintStripes.shader = stripesShader
            canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paintStripes)
        }
    }

    private fun createShader(): LinearGradient = with(stripedDrawable) {
        val position = .5f
        return LinearGradient(
            stripeWidth,
            tiltLeft,
            0f,
            tiltRight,
            intArrayOf(colorMain, colorSub),
            floatArrayOf(position, position),
            Shader.TileMode.REPEAT
        )
    }

    private fun createGradientShader(): LinearGradient = with(stripedDrawable) {
        return LinearGradient(
            stripeWidth, tiltLeft, 0f, tiltRight,
            colorMain, colorSub, Shader.TileMode.REPEAT
        )
    }

    private fun shiftColor(mainColor: Int, subColor: Int) = with(stripedDrawable) {
        colorMain = subColor
        colorSub = mainColor
    }

    // Start stripes animation
    fun start() {
        if (isRunning()) return
        initAnimator()
        animator?.start()
        stripedDrawable.isShowStripes = true
        invalidateSelf()
    }

    // Stop stripes animation
    fun stop() {
        if (!isRunning()) return
        animator?.cancel()
        animator = null
        stripedDrawable.isShowStripes = false
        invalidateSelf()
    }

    // Check if stripes animation running
    fun isRunning(): Boolean {
        return animator?.isStarted == true
    }
}
