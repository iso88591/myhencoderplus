package grg.learn.myhencoderplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import grg.learn.myhencoderplus.views.PieView

class PieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PieView(this))
    }
}