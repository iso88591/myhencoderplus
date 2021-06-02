package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.ext.dp
import kotlin.math.cos
import kotlin.math.sin


private val Radius = 100.dp
private const val LineOffset = 50
private const val Text = "abab"

class DrawTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.strokeCap = Paint.Cap.ROUND
        it.style = Paint.Style.STROKE
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.BLACK
        it.textSize = 78.dp
        it.textAlign = Paint.Align.CENTER
    }

    //测量文字 bounds
    private val bounds = Rect()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //背景
        mPaint.strokeWidth = 50f
        mPaint.color = Color.BLACK
        canvas.drawArc(
            width / 2 - Radius,
            height / 2 - Radius,
            width / 2 + Radius,
            height / 2 + Radius,
            0f, 360f,
            false,
            mPaint
        )

        mPaint.color = Color.BLUE
        canvas.drawArc(
            width / 2 - Radius,
            height / 2 - Radius,
            width / 2 + Radius,
            height / 2 + Radius,
            0f, 270f,
            false,
            mPaint
        )

        //x 轴
        mPaint.strokeWidth = 1f
        mPaint.color = Color.RED
        val endX = width / 2f + Radius + LineOffset
        val endY = height / 2f
        canvas.drawLine(
            width / 2f - Radius - LineOffset,
            height / 2f,
            width / 2f + Radius + LineOffset,
            height / 2f,
            mPaint
        )

        canvas.drawLine(
            endX,
            endY,
            endX + cos(Math.toRadians(210.0).toFloat()) * 20,
            endY + sin(Math.toRadians(210.0).toFloat()) * 20,
            mPaint
        )
        canvas.drawLine(
            endX,
            endY,
            endX + cos(Math.toRadians(150.0).toFloat()) * 20,
            endY + sin(Math.toRadians(150.0).toFloat()) * 20,
            mPaint
        )
        //y 轴
        val endX2 = width / 2f
        val endY2 = height / 2f + Radius + LineOffset

        canvas.drawLine(
            width / 2f,
            height / 2f - Radius - LineOffset,
            endX2,
            endY2,
            mPaint
        )
        canvas.drawLine(
            endX2,
            endY2,
            endX2 + cos(Math.toRadians(240.0).toFloat()) * 20,
            endY2 + sin(Math.toRadians(240.0).toFloat()) * 20,
            mPaint
        )
        canvas.drawLine(
            endX2,
            endY2,
            endX2 + cos(Math.toRadians(300.0).toFloat()) * 20,
            endY2 + sin(Math.toRadians(300.0).toFloat()) * 20,
            mPaint
        )

        val yOffset = yOffset()

        //画文字
        canvas.drawText(
            Text,
            0,
            Text.length,
            width / 2f,
            height / 2f + yOffset,
            textPaint
        )


        //它上部是 top到baseLine的 所以将它向下多移动 baseLine - top
        textPaint.getFontMetrics(fontMetrics)


        val textAlign = textPaint.textAlign
        textPaint.textAlign = Paint.Align.LEFT
        val topLeft = fontMetrics.leading - fontMetrics.top

        //左对齐
        canvas.drawText(
            Text,
            0,
            Text.length,
            0f,
            topLeft,
            textPaint
        )

        textPaint.getTextBounds(Text, 0, Text.length, bounds)
        //左对齐 左边缝隙
        canvas.drawText(
            Text,
            0,
            Text.length,
            -bounds.left.toFloat(),
            topLeft + 100,
            textPaint
        )
        textPaint.textAlign = textAlign

    }

    private val fontMetrics = Paint.FontMetrics()
    private fun yOffset(): Float {
        //静态测量
//        textPaint.getTextBounds(Text, 0, Text.length, bounds)
//        return -(bounds.bottom + bounds.top) / 2f

        //边界测量
        textPaint.getFontMetrics(fontMetrics)
        return -(fontMetrics.ascent + fontMetrics.descent) / 2

    }


}