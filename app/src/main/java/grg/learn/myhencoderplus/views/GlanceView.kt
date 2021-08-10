package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import androidx.core.graphics.withSave

class GlanceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val gradient = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(Color.TRANSPARENT, Color.BLUE)
    )

    private val gradientPercent = 0.25f
    var offset = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        val ani = ObjectAnimator.ofFloat(this,
            "offset",
            0.2f,
            0.75f)
        ani.repeatCount = -1
        ani.duration = 800
        ani.startDelay = 200
        ani.start()

    }

    override fun dispatchDraw(canvas: Canvas){
        super.dispatchDraw(canvas)
        gradient.setBounds(
            0,0,width,height
        )
        canvas.withSave {
            translate(0f,offset * height)
            clipRect(
                0f,
                0f,
                width.toFloat(),
                height * gradientPercent
            )
            gradient.draw(canvas)

        }

    }
}