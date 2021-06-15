package grg.learn.myhencoderplus.viewtry

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout

class ColorTransForm @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mCanvas = Canvas()

    private var bitmap: Bitmap? = null

    val paint = Paint().also {
        //[ a, b, c, d, e,
        //  f, g, h, i, j,
        //  k, l, m, n, o,
        //  p, q, r, s, t ]
        //通过计算， ColorMatrix 可以把要绘制的像素进行转换。对于颜色 [R, G, B, A] ，转换算法是这样的：
        //
        //R’ = a*R + b*G + c*B + d*A + e;
        //G’ = f*R + g*G + h*B + i*A + j;
        //B’ = k*R + l*G + m*B + n*A + o;
        //A’ = p*R + q*G + r*B + s*A + t;
        it.colorFilter = ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0.33f, 0.59f, 0.11f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )

    }

    override fun dispatchDraw(canvas: Canvas) {

        if (bitmap == null || bitmap?.width != mCanvas.width || bitmap?.height != mCanvas.height) {
            bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
            mCanvas.setBitmap(bitmap)
        }

        super.dispatchDraw(mCanvas)

        canvas.drawBitmap(bitmap!!, 0f, 0f, paint)

    }

}