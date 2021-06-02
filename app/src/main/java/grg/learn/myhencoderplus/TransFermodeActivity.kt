package grg.learn.myhencoderplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import grg.learn.myhencoderplus.views.TranFerModeView

class TransFermodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TranFerModeView(this))
    }
}