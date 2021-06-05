package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar

class Animate1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val AVATAR = 200.dp

    var angle = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {

        setOnClickListener {

            val animate = ObjectAnimator.ofFloat(this, "angle", 0f, 30f)
            animate.startDelay = 500
            animate.duration = 500
            animate.start()

        }
    }

    private val avatar = context.getAvatar(R.drawable.ic_avatar, AVATAR.toInt())

    override fun onDraw(canvas: Canvas) {

        //左边
        canvas.withSave {
            canvas.translate(
                -(-(width - AVATAR) / 2f - AVATAR / 2f),
                -(-(height - AVATAR) / 2f - AVATAR)
            )

            canvas.rotate(-angle / 2f, 0f, 0f)
            canvas.clipRect(
                -AVATAR / 2f,
                -AVATAR,
                0f,
                0f
            )
            canvas.translate(
                -(width - AVATAR) / 2f - AVATAR / 2f,
                -(height - AVATAR) / 2f - AVATAR
            )
            canvas.drawBitmap(
                avatar,
                (width - AVATAR) / 2f,
                (height - AVATAR) / 2f,
                null
            )
        }

        //右边
        canvas.withSave {
            canvas.translate(
                -(-(width - AVATAR) / 2f - AVATAR / 2f),
                -(-(height - AVATAR) / 2f - AVATAR)
            )

            canvas.rotate(angle / 2f, 0f, 0f)
            canvas.clipRect(
                0f,
                -AVATAR,
                AVATAR / 2f,
                0f
            )
            canvas.translate(
                -(width - AVATAR) / 2f - AVATAR / 2f,
                -(height - AVATAR) / 2f - AVATAR
            )
            canvas.drawBitmap(
                avatar,
                (width - AVATAR) / 2f,
                (height - AVATAR) / 2f,
                null
            )
        }


    }

}