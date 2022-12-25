package com.github.nikartm.support

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.github.nikartm.support.Util.Animation.animateView
import com.github.nikartm.support.constant.Constants.DEF_START_ANIM_DURATION
import com.github.nikartm.support.constant.Constants.DEF_STOP_ANIM_DURATION
import com.github.nikartm.support.constant.Constants.NO_INIT

/**
 * Striped process button
 * @author Ivan V on 29.03.2018.
 * @version 1.0
 */
class StripedProcessButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : AppCompatButton(context, attrs), Animatable {

    private lateinit var stripedDrawable: StripedDrawable
    private lateinit var animatedDrawable: AnimatedStripedDrawable
    private var state = State.STOP

    /**
     * Get current start animation duration
     * @return start duration in ms
     */
    var startAnimDuration = NO_INIT
        private set

    /**
     * Get current stop animation duration
     * @return stop duration in ms
     */
    var stopAnimDuration = NO_INIT
        private set

    /**
     * Get button stripe animation state
     * @return true if button can be animated
     */
    var isButtonAnimated = true
        private set

    private var defaultText: String = ""
    private var density = 0f

    init {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        density = context.resources.displayMetrics.density
        val attrController = AttributeController(context, attrs)
        stripedDrawable = attrController.stripedDrawable
        animatedDrawable = AnimatedStripedDrawable(stripedDrawable)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initView()
    }

    private fun initView() {
        defaultText = text.toString()
        when (state) {
            State.START -> start()
            State.STOP -> stop()
        }
    }

    override fun onDraw(canvas: Canvas) {
        background = animatedDrawable
        isEnabled = !isRunning
        super.onDraw(canvas)
    }

    override fun start() {
        state = State.START
        if (isRunning || !isAttachedToWindow) return
        isEnabled = isRunning
        animatedDrawable.start()
        animateButton(isRunning)
    }

    override fun stop() {
        state = State.STOP
        if (!isRunning || !isAttachedToWindow) return
        isEnabled = isRunning
        animatedDrawable.stop()
        animateButton(isRunning)
    }

    override fun isRunning(): Boolean {
        return isAttachedToWindow && animatedDrawable.isRunning()
    }

    /**
     * Set start animation duration in ms
     */
    fun setStartAnimDuration(startAnimDuration: Long): StripedProcessButton {
        this.startAnimDuration = startAnimDuration
        invalidate()
        return this
    }

    /**
     * Set stop animation duration in ms
     */
    fun setStopAnimDuration(stopAnimDuration: Long): StripedProcessButton {
        this.stopAnimDuration = stopAnimDuration
        invalidate()
        return this
    }

    /**
     * Get text when button has loading state
     * @return loading text
     */
    val loadingText: String?
        get() = stripedDrawable.loadingText

    /**
     * Set text when button has loading state
     * @param loadingText text when loading started
     */
    fun setLoadingText(loadingText: String?): StripedProcessButton {
        stripedDrawable.loadingText = loadingText
        invalidate()
        return this
    }

    /**
     * Set button stripe animation state
     * @param buttonAnimated if tru button can be animated
     */
    fun setButtonAnimated(buttonAnimated: Boolean): StripedProcessButton {
        isButtonAnimated = buttonAnimated
        invalidate()
        return this
    }

    /**
     * Get width of stripes
     */
    val stripeWidth: Float
        get() = stripedDrawable.stripeWidth

    /**
     * Set width of stripes
     * @param stripeWidth on view
     */
    fun setStripeWidth(stripeWidth: Float): StripedProcessButton {
        stripedDrawable.stripeWidth = stripeWidth * density
        invalidate()
        return this
    }

    /**
     * Get drawable background color
     */
    val colorBack: Int
        get() = stripedDrawable.colorBack

    /**
     * Set drawable background color
     * @param colorBack background color
     */
    fun setColorBack(colorBack: Int): StripedProcessButton {
        stripedDrawable.colorBack = colorBack
        invalidate()
        return this
    }

    /**
     * Get color the main stripe
     */
    val colorMain: Int
        get() = stripedDrawable.colorMain

    /**
     * Set color the main stripe
     * @param colorMain color of main stripe
     */
    fun setColorMain(colorMain: Int): StripedProcessButton {
        stripedDrawable.colorMain = colorMain
        invalidate()
        return this
    }

    /**
     * Get color the sub stripe
     */
    val colorSecond: Int
        get() = stripedDrawable.colorSub

    /**
     * Set color the sub stripe
     * @param colorSecond color of sub stripe
     */
    fun setColorSecond(colorSecond: Int): StripedProcessButton {
        stripedDrawable.colorSub = colorSecond
        invalidate()
        return this
    }

    /**
     * Get alpha stripes
     */
    val stripeAlpha: Float
        get() = stripedDrawable.stripeAlpha

    /**
     * Set alpha drawable stripes
     * @param alpha stripes
     */
    fun setStripeAlpha(alpha: Float): StripedProcessButton {
        stripedDrawable.stripeAlpha = alpha
        invalidate()
        return this
    }

    /**
     * Get drawable corner radius
     */
    val cornerRadius: Float
        get() = stripedDrawable.cornerRadius

    /**
     * Set drawable corner radius
     * @param cornerRadius radius
     */
    fun setCornerRadius(cornerRadius: Float): StripedProcessButton {
        stripedDrawable.cornerRadius = cornerRadius * density
        invalidate()
        return this
    }

    /**
     * Get duration of stripes animation
     */
    val stripeDuration: Int
        get() = stripedDrawable.stripeDuration

    /**
     * Set duration of stripes animation
     */
    fun setStripeDuration(stripeDuration: Int): StripedProcessButton {
        stripedDrawable.stripeDuration = stripeDuration
        invalidate()
        return this
    }

    /**
     * Get tilt of stripes
     */
    val tilt: Float
        get() = stripedDrawable.tilt

    /**
     * Set tilt of stripes
     * @param tilt of stripes
     */
    fun setTilt(tilt: Float): StripedProcessButton {
        stripedDrawable.tilt = tilt
        invalidate()
        return this
    }

    /**
     * Get state of tilt stripes. If true - tilt to left, false - tilt to right.
     */
    val isStripeRevert: Boolean
        get() = stripedDrawable.isStripeRevert

    /**
     * Get state of tilt stripes.
     * @param stripeRevert If true - tilt to left, false - tilt to right.
     */
    fun setStripeRevert(stripeRevert: Boolean): StripedProcessButton {
        stripedDrawable.isStripeRevert = stripeRevert
        invalidate()
        return this
    }

    /**
     * Get states of showing stripes
     */
    val isShowStripes: Boolean
        get() = stripedDrawable.isShowStripes

    /**
     * Set states of showing stripes
     * @param showStripes If true - stripes showing, false - stripes gone.
     */
    fun setShowStripes(showStripes: Boolean): StripedProcessButton {
        stripedDrawable.isShowStripes = showStripes
        invalidate()
        return this
    }

    /**
     * Get states of stripes appearance
     */
    val isStripeGradient: Boolean
        get() = stripedDrawable.isStripeGradient

    /**
     * Set the state of striped appearance of drawable
     * @param stripeGradient if true stripes has gradient style, false - flat strips
     */
    fun setStripeGradient(stripeGradient: Boolean): StripedProcessButton {
        stripedDrawable.isStripeGradient = stripeGradient
        invalidate()
        return this
    }

    private fun animateButton(startAnim: Boolean) {
        if (isButtonAnimated) {
            val duration = if (startAnim) {
                if (startAnimDuration == NO_INIT) DEF_START_ANIM_DURATION else startAnimDuration
            } else {
                if (stopAnimDuration == NO_INIT) DEF_STOP_ANIM_DURATION else stopAnimDuration
            }
            setCurrentText(startAnim)
            animateView(this, startAnim, duration)
        }
    }

    private fun setCurrentText(startAnim: Boolean) = with(stripedDrawable) {
        val currentText = when {
            startAnim -> if (!loadingText.isNullOrBlank()) loadingText else defaultText
            else -> defaultText
        }
        text = currentText
    }

    /**
     * State of launch methods with delay
     */
    private enum class State {
        START, STOP
    }
}
