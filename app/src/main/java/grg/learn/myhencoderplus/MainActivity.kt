package grg.learn.myhencoderplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import grg.learn.myhencoderplus.views.ColorsTextView
import grg.learn.myhencoderplus.views.TransformationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun instrument(view: View) {
        startActivity(Intent(this, InstrumentActivity::class.java))
    }

    fun pie(view: View) {
        startActivity(Intent(this, PieActivity::class.java))
    }

    fun transFerMode(view: View) {
        startActivity(Intent(this, TransFermodeActivity::class.java))
    }

    fun text1(view: View) {
        startActivity(Intent(this, DrawText1Activity::class.java))
    }

    fun geometricTransformation(view: View) {
        ShowViewActivity.start(this,TransformationView::class.java)
    }

    fun onePlus(view: View) {
        ShowViewActivity.start(this,ColorsTextView::class.java)
    }
}