package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.ext.dp
import kotlin.math.cos
import kotlin.math.sin

private val PIE_RADIUS = 100.dp

private val PIE_ANGLE = floatArrayOf(30f, 10f, 60f, 260f)
private val PIE_COLOR = intArrayOf(
    Color.BLACK,
    Color.BLUE,
    Color.CYAN,
    Color.YELLOW
)

private val DISTANCE = 30.dp

class PieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        var sumAngle = 0f
        for ((index, value) in PIE_ANGLE.withIndex()) {

            val angle = PIE_ANGLE[index]
            val color = PIE_COLOR[index]

            if (index == 2) {
                canvas.save()
                val transAngle = (sumAngle + angle / 2f).toDouble()
                canvas.translate(
                    (DISTANCE * cos(Math.toRadians(transAngle))).toFloat(),
                    (DISTANCE * sin(Math.toRadians(transAngle))).toFloat(),
                )
            }

            mPaint.color = color
            canvas.drawArc(
                width / 2f - PIE_RADIUS,
                height / 2f - PIE_RADIUS,
                width / 2f + PIE_RADIUS,
                height / 2f + PIE_RADIUS,
                sumAngle,
                angle,
                true,
                mPaint
            )

            if (index == 2) {
                canvas.restore()
            }

            sumAngle += angle

        }


    }

}