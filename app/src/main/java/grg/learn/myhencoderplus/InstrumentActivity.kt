package grg.learn.myhencoderplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import grg.learn.myhencoderplus.views.InstrumentView

class InstrumentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(InstrumentView(this))


    }
}