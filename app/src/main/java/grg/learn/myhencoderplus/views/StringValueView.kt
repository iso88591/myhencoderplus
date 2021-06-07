package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

val list = listOf<String>(
    "北京市",
    "天津市",
    "辽宁省沈阳市",
    "吉林省长春市",
    "黑龙江省哈尔滨市",
    "上海市",
    "江苏省南京市",
    "湖北省武汉市",
    "广东省广州市",
    "重庆市",
    "四川省成都市",
    "陕西省西安市"
)

class StringValueView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text: String = "北京市"
        set(value) {
            field = value
            invalidate()
        }

    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).also {
        it.textSize = 60f
        it.color = Color.BLACK

    }

    private val fontMetric = Paint.FontMetrics()

    class StringTypeEvaluator : TypeEvaluator<String> {
        override fun evaluate(
            fraction: Float,
            startValue: String,
            endValue: String
        ): String {

            val first = list.indexOf(startValue)
            val end = list.indexOf(endValue)

            val current = first + ((end - first) * fraction).toInt()
            return list[current]
        }

    }

    init {

        setOnClickListener {
            with(ObjectAnimator.ofObject(
                this,
                "text",
                StringTypeEvaluator(),
                "北京市",
                "陕西省西安市"
            )){

                startDelay = 500
                duration = 5000

                start()

            }
        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            paint.getFontMetrics(fontMetric)
            val textWidth = paint.measureText(text)

            drawText(
                text,
                0,
                text.length,
                (width - textWidth) / 2f,
                (height + (fontMetric.bottom - fontMetric.top) / 2 - fontMetric.bottom) / 2f,
                paint
            )

        }
    }

}