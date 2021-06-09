package grg.learn.myhencoderplus.viewtry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.getAvatar

class XfermodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
    }

    val w = 400
    private val bitmap = context.getAvatar(R.drawable.ic_avatar, w)

    val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {

        setLayerType(LAYER_TYPE_HARDWARE, null)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        defaultDraw(canvas)
        hardwareDraw(canvas)

//        canvas.withSave {
//
//            canvas.drawCircle(
//                width / 2f,
//                height / 2f,
//                w / 2f,
//                paint
//            )
//            paint.xfermode = xfermode
//            clipRect(
//                (width - 2) / 2f,
//                (height - 2) / 2f - w / 2f,
//                (width - 2) / 2f + w / 2f,
//                (height - 2) / 2f + w / 2f,
//            )
//            canvas.drawBitmap(
//                bitmap,
//                (width - w) / 2f,
//                (height - w) / 2f,
//                paint
//            )
//            paint.xfermode = null
//        }

    }

    fun defaultDraw(canvas: Canvas) = canvas.apply {

        val saveLayer = canvas.saveLayer(
            (width - 2) / 2f,
            (height - 2) / 2f - w / 2f,
            (width - 2) / 2f + w / 2f,
            (height - 2) / 2f + w / 2f, null
        )

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            w / 2f,
            paint
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(
            bitmap,
            (width - w) / 2f,
            (height - w) / 2f,
            paint
        )
        paint.xfermode = null

        canvas.restoreToCount(saveLayer)
    }

    fun hardwareDraw(canvas: Canvas) = canvas.apply {
        withSave {
            clipRect(
                (width - 2) / 2f,
                (height - 2) / 2f - w / 2f,
                (width - 2) / 2f + w / 2f,
                (height - 2) / 2f + w / 2f,
            )
            canvas.drawCircle(
                width / 2f,
                height / 2f,
                w / 2f,
                paint
            )
            paint.xfermode = xfermode
            canvas.drawBitmap(
                bitmap,
                (width - w) / 2f,
                (height - w) / 2f,
                paint
            )
            paint.xfermode = null
        }
    }


}