package grg.learn.myhencoderplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import grg.learn.myhencoderplus.views.DrawTextView
import grg.learn.myhencoderplus.views.MixAvatarAndTextView

class DrawText1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(DrawTextView(this))
        setContentView(MixAvatarAndTextView(this))



    }
}