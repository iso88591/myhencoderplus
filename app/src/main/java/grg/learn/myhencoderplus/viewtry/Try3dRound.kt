package grg.learn.myhencoderplus.viewtry

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave

class Try3dRound @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.STROKE
        it.strokeWidth = 2f
        it.textSize = 170f
        it.color = Color.BLACK
    }
    val camera = Camera()

    val str = "国国国国国国"
    val metrics = Paint.FontMetrics()

    init {

        val path = Path()


        PathDashPathEffect(
            path,
            100f,
            0f,
            PathDashPathEffect.Style.TRANSLATE
        )

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            val textWidth = paint.measureText(str)
            paint.getFontMetrics(metrics)

            withSave {

                translate(width / 2f, height / 2f)

                camera.save()
                camera.rotateX(45f)
                camera.applyToCanvas(this)
                camera.restore()

                translate(-width / 2f, -height / 2f)
                drawText(
                    str,
                    0,
                    str.length,
                    (width - textWidth) / 2f,
                    height / 2f + ((metrics.bottom - metrics.top) / 2f - metrics.bottom),
                    paint
                )
            }

            drawText(
                str,
                0,
                str.length,
                (width - textWidth) / 2f,
                height / 2f + ((metrics.bottom - metrics.top) / 2f - metrics.bottom),
                paint
            )
//            drawCircle(
//                width / 2f,
//                height / 2f,
//                width / 2f - 100,
//                paint
//            )

        }

    }


}