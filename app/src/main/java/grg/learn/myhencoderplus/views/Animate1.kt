package grg.learn.myhencoderplus.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar

class Animate1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val AVATAR = 200.dp

    private val avatar = context.getAvatar(R.drawable.ic_avatar, AVATAR.toInt())

    override fun onDraw(canvas: Canvas) {

        canvas.drawBitmap(
            avatar,
            (width - AVATAR) / 2f,
            (height - AVATAR) / 2f,
            null
        )

    }

}