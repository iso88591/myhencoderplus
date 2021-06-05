package grg.learn.myhencoderplus.views

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

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            RADIS,
            paint
        )

    }

}