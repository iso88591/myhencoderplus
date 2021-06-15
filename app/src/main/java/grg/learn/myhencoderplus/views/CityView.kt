package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

private val TextSize = floatArrayOf(36f, 48f, 55f)
private val Colors = intArrayOf(Color.RED, Color.GREEN, Color.YELLOW, Color.GRAY, Color.BLUE)

//fun FloatArray.random

class CityView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.textSize = TextSize.random()
        it.color = Colors.random()
        it.textAlign = Paint.Align.CENTER
    }

    private val color = Colors[(Colors.indexOf(paint.color) + 1) % Colors.size]

    private val metrics = Paint.FontMetrics()
    private var str: String = ""

    init {

        val arr =
            context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.text), defStyleAttr, 0)
        str = arr.getString(0) ?: ""
        arr.recycle()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val textWidth = paint.measureText(str).toInt()
        paint.getFontMetrics(metrics)

        setMeasuredDimension(
            textWidth + paddingLeft + paddingRight,
            (metrics.bottom - metrics.top).toInt() + paddingTop + paddingBottom
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(
            color
        )

        canvas?.drawText(
            str,
            0,
            str.length,
            width / 2f,
            height / 2f + ((metrics.bottom - metrics.top) / 2f - metrics.bottom),
            paint
        )

    }

}