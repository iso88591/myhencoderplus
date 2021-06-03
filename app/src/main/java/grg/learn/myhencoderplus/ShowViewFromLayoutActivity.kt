package grg.learn.myhencoderplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShowViewFromLayoutActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context, layoutId: Int) {
            val intent = Intent(context, ShowViewFromLayoutActivity::class.java)
            intent.putExtra("layout", layoutId)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stringExtra = intent.getIntExtra("layout", 0)
        if (stringExtra != 0) {
            setContentView(stringExtra)
        }

    }
}