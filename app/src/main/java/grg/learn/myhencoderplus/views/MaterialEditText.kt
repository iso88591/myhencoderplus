package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

//val SIZE = 30.dp

class MaterialEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.GRAY
        it.textAlign = Paint.Align.LEFT
    }
    val fontMetrics = Paint.FontMetrics()

    var textY: Float = 0f

    var fraction: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var lastIsEmpty = true

    init {

        hint = "提示文字"


        setPadding(
            paddingLeft,
            (paddingTop + super.getPaint().textSize * 1.5f).toInt(),
            paddingRight,
            paddingBottom
        )


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint.textSize = super.getPaint().textSize
        paint.getFontMetrics(fontMetrics)
        textY = (super.getPaint().textSize * 1.5f + fontMetrics.top) / 2f

    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        val currentIsEmpty = (text?.toString() ?: "").isEmpty()

        if (lastIsEmpty != currentIsEmpty) {
            if (currentIsEmpty) {
                //有->无
                ObjectAnimator.ofFloat(
                    this,
                    "fraction",
                    0f,
                    1f
                ).reverse()
            } else {
                //无->有
                ObjectAnimator.ofFloat(
                    this,
                    "fraction",
                    0f,
                    1f
                ).start()
            }
        }

        lastIsEmpty = currentIsEmpty

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.alpha = (fraction * 0xff).toInt()


        canvas.drawText(
            hint,
            0,
            hint.length,
            paddingLeft.toFloat(),
            -fontMetrics.top + (super.getPaint().textSize * 1.5f + fontMetrics.top) / 2f + textY * (1 - fraction),
            paint
        )


    }

}