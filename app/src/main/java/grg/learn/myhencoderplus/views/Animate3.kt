package grg.learn.myhencoderplus.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar

class Animate3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val W = 200.dp

    private val image = context.getAvatar(R.drawable.ic_avatar, W.toInt())

    var imageSize: Float = W
        set(value) {
            field = value
            invalidate()
        }

    var rotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    val sourceRect = Rect(
        0,
        0,
        W.toInt(),
        W.toInt()
    )
    val targetRect = RectF()

    init {

        setOnClickListener {

            val sizeAni = ObjectAnimator.ofFloat(this, "imageSize", W, W * 1.5f)
            val rotateAni = ObjectAnimator.ofFloat(this, "rotate", 0f, 180f, 0f)

            with(AnimatorSet()) {
                playTogether(
                    sizeAni,
                    rotateAni
                )
                startDelay = 500
                duration = 1000
                start()
            }

        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        targetRect.set(
            (width - imageSize) / 2f,
            (height - imageSize) / 2f,
            (width - imageSize) / 2f + imageSize,
            (height - imageSize) / 2f + imageSize
        )

        canvas.withSave {

            translate(
                -(-targetRect.left - targetRect.width() / 2f),
                -(-targetRect.top - targetRect.height() / 2f)
            )

            rotate(rotate, 0f, 0f)

            translate(
                -targetRect.left - targetRect.width() / 2f,
                -targetRect.top - targetRect.height() / 2f
            )

            canvas.drawBitmap(
                image,
                sourceRect,
                targetRect,
                null
            )

        }


    }

}