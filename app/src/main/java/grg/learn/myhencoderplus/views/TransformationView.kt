package grg.learn.myhencoderplus.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar
import kotlin.math.sqrt


private val Size = 200.dp

class TransformationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angle: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var foldAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    val image = context.getAvatar(R.drawable.ic_avatar, Size.toInt())

    val camera = Camera().also {
        it.setLocation(0f, 0f, -6 * context.resources.displayMetrics.density)
    }

    init {

        val ofFloat = ObjectAnimator.ofFloat(this, "angle", 0f, 50f)
        ofFloat.startDelay = 200
        ofFloat.duration = 2000

        val fold = ObjectAnimator.ofFloat(this, "foldAngle", 0f, 45f)
        fold.startDelay = 200
        fold.duration = 2000


        val animatorSet = AnimatorSet()
        animatorSet.play(ofFloat)
        animatorSet.play(fold)

        animatorSet.startDelay = 200
        animatorSet.duration = 2000
        animatorSet.start()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val imageX = (width - Size) / 2f
        val imageY = (height - Size) / 2f

        val sqrt2 = sqrt(2f)

        canvas.save()
        canvas.translate((imageX + Size / 2f), (imageY + Size / 2f))
        canvas.rotate(-foldAngle, 0f, 0f)
        canvas.clipRect(
            -Size / 2f * sqrt2,
            -Size / 2f * sqrt2,
            Size / 2f * sqrt2,
            1f
        )
        canvas.rotate(foldAngle, 0f, 0f)
        canvas.translate(-(imageX + Size / 2f), -(imageY + Size / 2f))
        canvas.drawBitmap(
            image,
            imageX,
            imageY,
            null
        )
        canvas.restore()


        canvas.save()
        camera.save()

        canvas.translate((imageX + Size / 2f), (imageY + Size / 2f))
        canvas.rotate(-foldAngle, 0f, 0f)
        camera.rotateX(angle)
        camera.applyToCanvas(canvas)


        canvas.clipRect(
            -Size / 2f * sqrt2,
            0f,
            Size / 2f * sqrt2,
            Size / 2f * sqrt2
        )
        canvas.rotate(foldAngle, 0f, 0f)
        canvas.translate(-(imageX + Size / 2f), -(imageY + Size / 2f))
        canvas.drawBitmap(
            image,
            imageX,
            imageY,
            null
        )
        camera.restore()
        canvas.restore()

    }

}