package grg.learn.myhencoderplus.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

class SquareView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

//        val resolveW = resolveSize(measuredWidth, widthMeasureSpec)
//        val resolveH = resolveSize(measuredHeight, heightMeasureSpec)

        val size = min(measuredWidth, measuredHeight)

        setMeasuredDimension(
            size,
            size,
        )

    }


}