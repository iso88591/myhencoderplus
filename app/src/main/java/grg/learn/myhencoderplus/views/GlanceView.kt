package grg.learn.myhencoderplus.views

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.graphics.withSave

class GlanceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val gradient = GradientDrawable(
        GradientDrawable.Orientation.TOP_BOTTOM,
        intArrayOf(Color.TRANSPARENT, Color.BLUE)
    )

    private val ringWidth = 20

    private val gradientPercent = 0.25f
    var offset = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var radius: Float = 0f
    private val roundPath = Path()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.strokeWidth = ringWidth.toFloat()
        this.style = Paint.Style.STROKE
    }

    init {
        val ani = ObjectAnimator.ofFloat(
            this,
            "offset",
            0.2f,
            0.75f
        )
        ani.repeatCount = -1
        ani.duration = 800
        ani.startDelay = 200
        ani.start()


    }

//    private val ringDrawable = ContextCompat.getDrawable(context, R.drawable.ic_round_ring)!!

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        radius = width / 2f - ringWidth
        roundPath.addCircle(width / 2f, height / 2f, radius, Path.Direction.CW)

        mPaint.shader = LinearGradient(
            width/2f,
            0f,
            width/2f,
            height.toFloat(),
            Color.BLUE,
            Color.BLACK,
            Shader.TileMode.CLAMP
        )

    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        gradient.setBounds(
            ringWidth, ringWidth, width - ringWidth, height - ringWidth
        )
        canvas.withSave {
            clipPath(roundPath)
            translate(0f, offset * height)
            clipRect(
                0f,
                0f,
                width.toFloat(),
                height * gradientPercent
            )
            gradient.draw(canvas)

        }

        canvas.drawCircle(width/2f, height/2f, radius, mPaint)

//        ringDrawable.setBounds(
//            0,0,
//            width,
//            height
//        )
//        ringDrawable.draw(canvas)

    }
}