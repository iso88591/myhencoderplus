package grg.learn.myhencoderplus.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.getAvatar


private const val Text =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi iaculis, orci molestie finibus iaculis, neque urna venenatis velit, ac luctus leo magna non augue. Donec eget imperdiet massa, sit amet malesuada turpis. Nulla facilisi. Morbi ligula odio, venenatis nec pulvinar eu, vulputate vel dui. Nam ut nunc id orci fermentum ornare sed ut nibh. Praesent condimentum leo pellentesque, tristique tortor a, auctor mauris. Suspendisse mollis felis et nibh tristique cursus. Praesent faucibus orci id varius finibus. Suspendisse eu turpis sollicitudin ante lacinia tristique vel eget nisi. Donec sed condimentum felis, ac ullamcorper magna. Mauris pharetra libero nisl, non ultrices enim efficitur in. Suspendisse eu ornare nulla, aliquam condimentum massa. Morbi consequat pretium risus id porta."
private const val AVATAR_SIZE = 120f

class MixAvatarAndTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).also {
        it.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            18f,
            Resources.getSystem().displayMetrics
        )
    }

    private val imagePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val measureWidthArr = FloatArray(1)
    private val fontMetrics = Paint.FontMetrics()

    private val avatar = context.getAvatar(R.drawable.ic_avatar, AVATAR_SIZE.toInt())

    private lateinit var sl: StaticLayout

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sl = StaticLayout(
            Text,
            textPaint,
            width,
            Layout.Alignment.ALIGN_CENTER,
            1f,
            1f,
            false
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        sl.draw(canvas)

        val imageX = width - AVATAR_SIZE
        val imageY = AVATAR_SIZE

        canvas.drawBitmap(
            avatar,
            imageX,
            imageY,
            imagePaint
        )

        textPaint.getFontMetrics(fontMetrics)
        var start = 0
        var line = 0
        while (start < Text.length) {

            val curY = -fontMetrics.top + line * textPaint.fontSpacing
            val curTextTop = curY + fontMetrics.top

            val w: Float =
                if ((curY in imageY..(imageY + AVATAR_SIZE)) || (curTextTop in imageY..(imageY + AVATAR_SIZE))) {
                    width - AVATAR_SIZE - 10
                } else {
                    width.toFloat()
                }

            val len = textPaint.breakText(
                Text,
                start,
                Text.length,
                true,
                w,
                measureWidthArr
            )

            canvas.drawText(
                Text,
                start,
                start + len,
                0f,
                -fontMetrics.top + line * textPaint.fontSpacing,
                textPaint
            )

            line++
            start += len

        }

    }

}