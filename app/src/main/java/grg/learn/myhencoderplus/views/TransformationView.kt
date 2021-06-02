package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import grg.learn.myhencoderplus.R
import grg.learn.myhencoderplus.ext.dp
import grg.learn.myhencoderplus.ext.getAvatar


private val Size = 200.dp

class TransformationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var angle: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    val image = context.getAvatar(R.drawable.ic_avatar, Size.toInt())

    val camera = Camera().also {
        it.setLocation(0f, 0f, -6 * context.resources.displayMetrics.density)
    }

    init {

        val ofFloat = ObjectAnimator.ofFloat(this, "angle", 0f, 60f)
        ofFloat.startDelay = 200
        ofFloat.duration = 2000
        ofFloat.start()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val imageX = (width - Size) / 2f
        val imageY = (height - Size) / 2f

        canvas.save()
        canvas.clipRect(
            imageX,
            imageY,
            imageX + Size,
            imageY + Size / 2f + 1
        )
        canvas.drawBitmap(
            image,
            imageX,
            imageY,
            null
        )
        canvas.restore()

        canvas.save()
        camera.save()

        canvas.translate(width / 2f, height / 2f)
        camera.rotateX(angle)
        camera.applyToCanvas(canvas)

        canvas.translate(-width / 2f, -height / 2f)
        camera.restore()

        canvas.clipRect(
            imageX,
            imageY + Size / 2f,
            imageX + Size,
            imageY + Size
        )

        canvas.drawBitmap(
            image,
            imageX,
            imageY,
            null
        )
        canvas.restore()

        //canvas.save();
        //
        //camera.save(); // 保存 Camera 的状态
        //camera.rotateX(30); // 旋转 Camera 的三维空间
        //canvas.translate(centerX, centerY); // 旋转之后把投影移动回来
        //camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        //canvas.translate(-centerX, -centerY); // 旋转之前把绘制内容移动到轴心（原点）
        //camera.restore(); // 恢复 Camera 的状态
        //
        //canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        //canvas.restore();


    }

}