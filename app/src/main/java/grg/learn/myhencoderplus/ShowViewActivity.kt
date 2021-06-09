package grg.learn.myhencoderplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class ShowViewActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context, clazz: Class<*>) {
            val intent = Intent(context, ShowViewActivity::class.java)
            intent.putExtra("Class", clazz.canonicalName)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stringExtra = intent.getStringExtra("Class")
        val forName = Class.forName(stringExtra)
        val declaredConstructor = forName.getDeclaredConstructor(Context::class.java)
        val newInstance = declaredConstructor.newInstance(this) as View
        setContentView(newInstance)
//        setContentView(FrameLayout(this).also {
//
//            newInstance.layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            it.addView(newInstance)
//
//        })

    }
}