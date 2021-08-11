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

    /**
     * create by iso88591 at 2021/8/11 🙃
     * 配置 圆环的宽度
     */
    private val ringWidth = 20

    /** 扫描的高度比 **/
    private val gradientPercent = 0.25f
    var offset = 0f
        set(value) {
            field = value
            invalidate()
        }

    /** 半径 **/
    private var radius: Float = 0f
    private val roundPath = Path()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.strokeWidth = ringWidth.toFloat()
        this.style = Paint.Style.STROKE
    }

    /** 提供给child的画布 **/
    private val childCanvas = Canvas()
    private var childBcBitmap: Bitmap? = null
    private val childBcXfer = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val childRoundPaint = Paint(mPaint).apply {
        strokeWidth = 0f
        style = Paint.Style.FILL
    }

    init {
        /**
         * create by iso88591 at 2021/8/11 🙃
         * 扫描动画
         *
         */
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

        /** 软件加速 **/
        setLayerType(LAYER_TYPE_SOFTWARE,null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        radius = width / 2f - ringWidth/2f
        roundPath.addCircle(width / 2f, height / 2f, radius, Path.Direction.CW)

        /** 配置着色器 **/
        mPaint.shader = LinearGradient(
            width / 2f,
            0f,
            width / 2f,
            height.toFloat(),
            Color.parseColor("#330EC875"),
            Color.parseColor("#CC09BAD4"),
            Shader.TileMode.CLAMP
        )

        if (childBcBitmap == null || childBcBitmap?.width != width || childBcBitmap?.height != height){
            childBcBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888)
        }
        childCanvas.setBitmap(childBcBitmap)

    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(childCanvas)

        canvas.withSave {
            drawRoundRect(
                ringWidth.toFloat(),
                ringWidth.toFloat(),
                width.toFloat()-ringWidth,
                width.toFloat()-ringWidth,
                width/2f,
                height/2f,
                childRoundPaint
            )
            childRoundPaint.xfermode = childBcXfer
//            childBcXfer
            childBcBitmap?.let {
                drawBitmap(it,0f,0f,childRoundPaint)
            }
            childRoundPaint.xfermode = null
        }

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

        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint)

    }
}