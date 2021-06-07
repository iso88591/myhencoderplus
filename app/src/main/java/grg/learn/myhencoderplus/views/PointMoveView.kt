package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.ext.dp

class PointMoveView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var point: PointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL
        it.strokeWidth = 150f
        it.color = Color.BLACK
        it.strokeCap = Paint.Cap.ROUND
    }

    init {

        setOnClickListener {

            with(
                ObjectAnimator.ofObject(
                    this,
                    "point",
                    PointTypeEvaluator(),
                    PointF(0f, 0f),
                    PointF(200.dp, 100.dp),
                )
            ) {

                startDelay = 500
                duration = 1000
                start()

            }


        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            drawPoint(
                point.x,
                point.y,
                paint
            )

        }

    }

    //TypeEvaluator
    class PointTypeEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(
            fraction: Float,
            startValue: PointF, endValue: PointF
        ): PointF {
            val startX = startValue.x
            val startY = startValue.y

            val startX2 = endValue.x
            val startY2 = endValue.y

            return PointF(
                startX + (startX2 - startX) * fraction,
                startY + (startY2 - startY) * fraction,
            )
        }

    }

}