package grg.learn.myhencoderplus.viewgroups

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import androidx.core.view.children
import grg.learn.myhencoderplus.R
import kotlin.math.max

class CityGroupLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (child in children) {
            val rect = child.getTag(R.id.viewTagData) as Rect
            child.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMax = MeasureSpec.getSize(widthMeasureSpec)

        var widthSum = 0
        var heightSum = 0
        var currentMaxHeight = 0

        for (child in children) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
            val measuredWidth = child.measuredWidth
            val measuredHeight = child.measuredHeight

            if (widthSum + measuredWidth > widthMax) {
                //换行
                heightSum += currentMaxHeight
                widthSum = 0
                currentMaxHeight = 0
            }

            currentMaxHeight = max(currentMaxHeight, measuredHeight)

            val rect = (child.getTag(R.id.viewTagData) as Rect?) ?: Rect()
            rect.set(widthSum, heightSum, widthSum + measuredWidth, heightSum + measuredHeight)
            child.setTag(R.id.viewTagData, rect)

            widthSum += measuredWidth

        }

        heightSum += currentMaxHeight

        setMeasuredDimension(
            widthMax,
            heightSum
        )

    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }
}