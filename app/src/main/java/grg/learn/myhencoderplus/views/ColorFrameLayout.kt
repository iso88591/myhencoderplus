package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout

class ColorFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    val mCanvas = Canvas()

    lateinit var bitmap: Bitmap
    val xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)

    var move: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL
        it.color = Color.RED
    }

    var ofFloat: ObjectAnimator? = null

    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (ofFloat == null && width > 0) {

            ofFloat = ObjectAnimator.ofFloat(this, "move", 0f, width.toFloat())

            ofFloat!!.duration = 500
            ofFloat!!.repeatCount = -1
            ofFloat!!.startDelay = 1000
            ofFloat!!.start()

        }

    }

    override fun dispatchDraw(canvas: Canvas) {

        if (width <= 0 || height <= 0) {
            super.dispatchDraw(canvas)
        } else {
            //清屏
            mCanvas.drawColor(Color.TRANSPARENT)
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            mCanvas.setBitmap(bitmap)
            super.dispatchDraw(mCanvas)

            colorDraw(canvas)
        }

    }

    private fun colorDraw(canvas: Canvas) {

        val saveLayer = canvas.saveLayer(null, null)
        canvas.drawBitmap(bitmap, 0f, 0f, null)

        paint.xfermode = xfermode
        canvas.drawRect(
            0f + move,
            0f,
            10f + move,
            height.toFloat(),
            paint
        )
        paint.xfermode = null

        canvas.restoreToCount(saveLayer)
    }


}