package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import grg.learn.myhencoderplus.ext.dp
import kotlin.math.cos
import kotlin.math.sin


//底部角度
private const val BOTTOM_ANGLE = 60f
private val INSTRUMENT_RADIUS = 100.dp

private val RING_LEN = 80.dp

class InstrumentView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val arcPath = Path()
    private lateinit var pathEffect: PathEffect

    //暂定 20个节点
    val divideCount = 20

    val ringCurrentPosition = 3

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.BLUE
        it.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        arcPath.reset()
        val xCenter = width / 2f
        val yCenter = height / 2f
        arcPath.addArc(
            RectF(
                xCenter - INSTRUMENT_RADIUS,
                yCenter - INSTRUMENT_RADIUS,
                xCenter + INSTRUMENT_RADIUS,
                yCenter + INSTRUMENT_RADIUS
            ),
            90 + (BOTTOM_ANGLE / 2f),
            360 - BOTTOM_ANGLE
        )

        val pathMeasure = PathMeasure(arcPath, false)
        val length = pathMeasure.length


        val effectPath = Path().also {
            it.addRect(RectF(0f, 0f, 2f, 15f), Path.Direction.CW)
        }
        val divideDistance = (length - 2f) / (divideCount - 1)

        pathEffect = PathDashPathEffect(
            effectPath,
            divideDistance,
            0f,
            PathDashPathEffect.Style.ROTATE
        )

    }

    private fun position2Angle(position: Int): Float {
        val start = 90 + BOTTOM_ANGLE / 2
        val perAngle = (360 - BOTTOM_ANGLE) / (divideCount - 1)
        return start + position * perAngle
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(arcPath, mPaint)

        mPaint.pathEffect = pathEffect
        canvas.drawPath(arcPath, mPaint)
        mPaint.pathEffect = null

        val position2Angle = position2Angle(ringCurrentPosition).toDouble()


        Log.e("TAG", "onDraw: angle=${position2Angle}", )
        Log.e("TAG", "onDraw: cos=${cos(position2Angle)}", )
        Log.e("TAG", "onDraw: sin=${sin(position2Angle)}", )

        //指针
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2f + cos(Math.toRadians(position2Angle).toFloat())* RING_LEN,
            height / 2f + sin(Math.toRadians(position2Angle)).toFloat() * RING_LEN,
            mPaint
        )

    }
}