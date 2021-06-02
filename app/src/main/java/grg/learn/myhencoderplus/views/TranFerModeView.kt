package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.getAvatar


private const val AVATAR_WIDTH = 300

class TranFerModeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val avatar: Bitmap = context.getAvatar(R.drawable.ic_avatar, AVATAR_WIDTH)

    private val xFermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val layer = canvas.saveLayer(
            (width - AVATAR_WIDTH) / 2f,
            (height - AVATAR_WIDTH) / 2f,
            (width - AVATAR_WIDTH) / 2f + AVATAR_WIDTH,
            (height - AVATAR_WIDTH) / 2f + AVATAR_WIDTH, null
        )
        mPaint.xfermode = null
        canvas.drawArc(
            (width - AVATAR_WIDTH) / 2f,
            (height - AVATAR_WIDTH) / 2f,
            (width - AVATAR_WIDTH) / 2f + AVATAR_WIDTH,
            (height - AVATAR_WIDTH) / 2f + AVATAR_WIDTH,
            0f,
            360f,
            true,
            mPaint
        )

        mPaint.xfermode = xFermode

        canvas.drawBitmap(
            avatar,
            (width - AVATAR_WIDTH) / 2f,
            (height - AVATAR_WIDTH) / 2f,
            mPaint
        )
        canvas.restoreToCount(layer)

    }

}