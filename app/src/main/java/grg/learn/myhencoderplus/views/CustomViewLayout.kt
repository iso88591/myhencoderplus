package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomViewLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    val Size = 300f
    val PaddingL = 200
    val PaddingT = 650

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            resolveSize((PaddingL + Size).toInt() * 2, widthMeasureSpec),
            resolveSize((PaddingT + Size).toInt() * 2, heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            PaddingL + Size,
            PaddingT + Size,
            Size,
            paint
        )

    }


}