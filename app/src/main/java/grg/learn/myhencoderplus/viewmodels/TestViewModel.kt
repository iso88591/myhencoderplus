package grg.learn.myhencoderplus.viewmodels

import android.util.Log
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import grg.learn.myhencoderplus.net.Api
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {


//    private var call: LiveDataScope<String>? = null

//    var wq: String = ""
//        set(value) {
//            field = value
//            Log.e("TAG", "=========: ${value}", )
//            viewModelScope.launch {
//                Log.e("TAG", "=========: ${value}", )
//                call?.emit(Api.INSTANCE.wq(value))
//            }
//        }
//
//    val data = liveData<String> {
//        call = this
//    }

    val flow = flow<String> {

        emit(Api.INSTANCE.wq("手表"))
    }

    fun wq(wq:String){

        viewModelScope.launch {


        }
    }


}