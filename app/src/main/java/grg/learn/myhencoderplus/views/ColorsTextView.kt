package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import grg.learn.myhencoderplus.ext.sp
import kotlin.math.abs
import kotlin.math.tan

class ColorsTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text: String = "OnePlus 浏览器"

    //切的角度 <90度
    var clipAngle = 45

    //切到哪里了
    var clipDistance = 0f
        set(value) {
            field = value
            invalidate()
        }

    var clipWidth = 100f


    //文字的宽度
    var textWidth = 0f

    //裁切的范围
    val clipPath = Path()


    val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).also {
        it.color = Color.BLACK
        it.textSize = 30.sp
        it.textAlign = Paint.Align.CENTER
    }

    lateinit var textMetrics: Paint.FontMetrics

    init {

        textMetrics = textPaint.fontMetrics
        textWidth = textPaint.measureText(text)

        val ofFloat = ObjectAnimator.ofFloat(this, "clipDistance", 0f, textWidth + clipWidth)
        ofFloat.startDelay = 1000
        ofFloat.duration = 1300
        ofFloat.interpolator = AccelerateDecelerateInterpolator()
        ofFloat.repeatCount = -1
        ofFloat.start()


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val textBottomY = height / 2f + abs(textMetrics.top) / 2f

//        //字的范围
        val textL = width / 2f - textWidth / 2f
        val textT = textBottomY - abs(textMetrics.top)
        val textR = width / 2f + textWidth / 2f
        val textB = textBottomY + textMetrics.bottom

        val textHeight = textB - textT
        val tanWidth = textHeight / tan(Math.toRadians(clipAngle.toDouble())).toFloat()

        clipPath.reset()
//        //左上角点
        clipPath.moveTo(
            textL - tanWidth - clipWidth + clipDistance,
            textT
        )

//        //右上角点
        clipPath.lineTo(
            textL + clipDistance + clipWidth - tanWidth - clipWidth,
            textT
        )

//        //右下角点
        clipPath.lineTo(
            -tanWidth - clipWidth + textL + clipDistance + clipWidth + textHeight / tan(
                Math.toRadians(
                    clipAngle.toDouble()
                )
            ).toFloat(),
            textB
        )
//        //左下角点
        clipPath.lineTo(
            -tanWidth - clipWidth + textL + clipDistance + clipWidth + textHeight / tan(
                Math.toRadians(
                    clipAngle.toDouble()
                )
            ).toFloat() - clipWidth,
            textB
        )
//        //回归左上角
        clipPath.lineTo(
            textL + clipDistance - tanWidth - clipWidth,
            textT
        )
        clipPath.close()

        canvas.save()
        canvas.clipPath(clipPath, Region.Op.DIFFERENCE)
        textPaint.color = Color.BLACK
        canvas.drawText(
            text,
            0,
            text.length,
            width / 2f,
            textBottomY,
            textPaint
        )
        canvas.restore()




        canvas.save()
        canvas.clipPath(clipPath)

        textPaint.color = Color.RED
        canvas.drawText(
            text,
            0,
            text.length,
            width / 2f,
            textBottomY,
            textPaint
        )

        canvas.restore()

    }


}