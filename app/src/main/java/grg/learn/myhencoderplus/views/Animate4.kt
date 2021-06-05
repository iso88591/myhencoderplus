package grg.learn.myhencoderplus.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar
import grg.learn.myhencoderplus.ext.withSave
import kotlin.math.sqrt

class Animate4 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val W = 200.dp

    private val image = context.getAvatar(R.drawable.ic_avatar, W.toInt())

    private val camera = Camera().also {
        it.setLocation(0f, 0f, -6 * context.resources.displayMetrics.density)
    }

    var topRotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    var bottomRotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    //斜着的角度
    var obliqueAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {

//        setOnClickListener {
//
//            topRotate = 0f
//            obliqueAngle = 0f
//            bottomRotate = 0f
//            invalidate()
//
//            with(AnimatorSet()) {
//                playSequentially(
//                    ObjectAnimator.ofFloat(this@Animate4, "bottomRotate", 0f, 45f),
//                    ObjectAnimator.ofFloat(this@Animate4, "obliqueAngle", 0f, 270f),
//                    ObjectAnimator.ofFloat(this@Animate4, "topRotate", 0f, -45f),
//                )
//                startDelay = 500
//                duration = 2000
//                start()
//            }
//
//        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val rate = sqrt(2f)

        canvas.withSave {

            translate(
                width / 2f,
                height / 2f
            )
            rotate(-obliqueAngle, 0f, 0f)

            camera.withSave {
                it.rotateX(topRotate)
                it.applyToCanvas(this)
            }

            clipRect(
                -W / 2f * rate,
                -W / 2f * rate,
                W / 2f * rate,
                1f
            )
            rotate(obliqueAngle, 0f, 0f)

            translate(
                -width / 2f,
                -height / 2f
            )
            drawBitmap(
                image,
                (width - W) / 2f,
                (height - W) / 2f,
                null
            )

        }

        canvas.withSave {

            translate(
                width / 2f,
                height / 2f
            )

            rotate(-obliqueAngle, 0f, 0f)
            camera.withSave {
                it.rotateX(bottomRotate)
                it.applyToCanvas(this)
            }

            clipRect(
                -W / 2f * rate,
                0f,
                W / 2f * rate,
                W / 2f * rate
            )

            rotate(obliqueAngle, 0f, 0f)

            translate(
                -width / 2f,
                -height / 2f
            )
            drawBitmap(
                image,
                (width - W) / 2f,
                (height - W) / 2f,
                null
            )

        }


    }

}