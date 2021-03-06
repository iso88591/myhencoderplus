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
     * create by iso88591 at 2021/8/11 ð
     * éç½® åç¯çå®½åº¦
     */
    private val ringWidth = 20

    /** æ«æçé«åº¦æ¯ **/
    private val gradientPercent = 0.25f
    var offset = 0f
        set(value) {
            field = value
            invalidate()
        }

    /** åå¾ **/
    private var radius: Float = 0f
    private val roundPath = Path()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.strokeWidth = ringWidth.toFloat()
        this.style = Paint.Style.STROKE
    }

    /** æä¾ç»childçç»å¸ **/
    private val childCanvas = Canvas()
    private var childBcBitmap: Bitmap? = null
    private val childBcXfer = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val childRoundPaint = Paint(mPaint).apply {
        strokeWidth = 0f
        style = Paint.Style.FILL
    }

    init {
        /**
         * create by iso88591 at 2021/8/11 ð
         * æ«æå¨ç»
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

        /** è½¯ä»¶å é **/
        setLayerType(LAYER_TYPE_SOFTWARE,null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        radius = width / 2f - ringWidth/2f
        roundPath.addCircle(width / 2f, height / 2f, radius-ringWidth/2f, Path.Direction.CW)

        /** éç½®çè²å¨ **/
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