package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar
import kotlin.math.sqrt

/**
 *
 * 组合动画
 *
 */
class ConcatAnimate1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    var rotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val bitmapSize = 200.dp
    private val bitmap = context.getAvatar(R.drawable.ic_avatar, bitmapSize.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {

    }
    private val camera = Camera().also {
        it.setLocation(0f, 0f, -6 * context.resources.displayMetrics.density)
    }

    init {

        setOnClickListener {


            with(
                ObjectAnimator.ofPropertyValuesHolder(
                    this,
                    PropertyValuesHolder.ofFloat(
                        "bottomFlip",
                        0f, 60f
                    ),
                    PropertyValuesHolder.ofFloat(
                        "rotate",
                        0f, 270f
                    ),
                    PropertyValuesHolder.ofFloat(
                        "topFlip",
                        0f, 60f
                    )
                )
            ) {

                startDelay = 500
                duration = 2000
                start()
            }


//            with(AnimatorSet()){
//
//                playTogether(
//                    ObjectAnimator.ofFloat(
//                        this@ConcatAnimate1,
//                        "bottomFlip",
//                        0f,
//                        60f
//                    ),
//                    ObjectAnimator.ofFloat(
//                        this@ConcatAnimate1,
//                        "rotate",
//                        0f,
//                        270f
//                    ),
//                    ObjectAnimator.ofFloat(
//                        this@ConcatAnimate1,
//                        "topFlip",
//                        0f,
//                        60f
//                    ),
//
//
//                )
//
//                startDelay = 500
//                duration = 2000
//                start()
//            }

        }

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            val size = bitmapSize / 2f * sqrt(2f)

            withSave {

                translate(
                    width / 2f,
                    height / 2f
                )
                rotate(-rotate, 0f, 0f)
                camera.save()
                camera.rotateX(-topFlip)
                camera.applyToCanvas(this)
                camera.restore()
                clipRect(
                    -size,
                    -size,
                    size,
                    1f,
                )
                rotate(rotate, 0f, 0f)
                translate(
                    -width / 2f,
                    -height / 2f
                )

                drawBitmap(
                    bitmap,
                    (width - bitmapSize) / 2f,
                    (height - bitmapSize) / 2f,
                    paint
                )
            }


            withSave {

                translate(
                    width / 2f,
                    height / 2f
                )
                rotate(-rotate, 0f, 0f)
                camera.save()
                camera.rotateX(bottomFlip)
                camera.applyToCanvas(this)
                camera.restore()
                clipRect(
                    -size,
                    0f,
                    size,
                    size,
                )
                rotate(rotate, 0f, 0f)
                translate(
                    -width / 2f,
                    -height / 2f
                )

                drawBitmap(
                    bitmap,
                    (width - bitmapSize) / 2f,
                    (height - bitmapSize) / 2f,
                    paint
                )
            }


        }

    }

}