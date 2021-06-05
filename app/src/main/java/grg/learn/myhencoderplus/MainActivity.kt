package grg.learn.myhencoderplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import grg.learn.myhencoderplus.viewmodels.TestViewModel
import grg.learn.myhencoderplus.views.*

class MainActivity : AppCompatActivity() {

    val vm by viewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(NestedScrollView(this).also {
            it.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            it.addView(LinearLayout(it.context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.VERTICAL

                button(
                    "OnePlus浏览器特效 加强版",
                    R.layout.layout_color_framlayout
                )

                buttonView(
                    "OnePlus浏览器特效",
                    ColorsTextView::class.java
                )
                buttonView(
                    "几何变换",
                    TransformationView::class.java
                )
                button(
                    "画文字1",
                    TransFermodeActivity::class.java
                )

                button(
                    "画文字1",
                    TransFermodeActivity::class.java
                )

                button(
                    "仪表盘",
                    InstrumentActivity::class.java
                )

                buttonView(
                    "饼图",
                    PieActivity::class.java
                )

                buttonView(
                    "动画 1 简单的属性动画 圆放大缩小",
                    Animate2::class.java
                )

                buttonView(
                    "动画 2 扔物线裂开了",
                    Animate1::class.java
                )

                buttonView(
                    "动画 3 组合动画",
                    Animate3::class.java
                )

                buttonView(
                    "动画 3 组合动画2",
                    Animate4::class.java
                )


            })

        })

    }

    fun ViewGroup.button(text: String, layout: Int) = button(text) {
        ShowViewFromLayoutActivity.start(this@MainActivity, layout)
    }

    fun ViewGroup.button(text: String, clazz: Class<*>) = button(text) {
        startActivity(Intent(this@MainActivity, clazz))
    }

    fun ViewGroup.buttonView(text: String, clazz: Class<*>) = button(text) {
        ShowViewActivity.start(this@MainActivity, clazz)
    }

    fun ViewGroup.button(text: String, click: View.OnClickListener) {
        addView(Button(context).also {
            it.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
            )
            it.text = text
            it.setOnClickListener(click)
        })
    }


}