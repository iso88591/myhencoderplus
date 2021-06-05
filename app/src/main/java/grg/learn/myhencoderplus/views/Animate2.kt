package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.ext.dp

class Animate2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var RADIS = 200f
        set(value) {
            field = value
            invalidate()
        }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.BLUE
        it.style = Paint.Style.FILL
    }

    init {
        setOnClickListener {

            val an = ObjectAnimator.ofFloat(this, "RADIS", 200f, 400f)
            an.startDelay = 400
            an.duration = 1000
            an.start()

        }
    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            RADIS,
            paint
        )

    }

}