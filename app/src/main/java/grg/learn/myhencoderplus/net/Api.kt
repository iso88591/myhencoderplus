package grg.learn.myhencoderplus.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //s?wd=手表
    @GET("s")
    suspend fun wq(@Query("wd") wd: String): String

    companion object {

        val INSTANCE by lazy {
            Retrofit.Builder()
                .also {
                    //scalars
                    it.addConverterFactory(ScalarsConverterFactory.create())
                    it.addConverterFactory(GsonConverterFactory.create())
                    it.baseUrl("http://www.baidu.com/")
                }
                .client(OkHttpClient.Builder()
                    .also {
                        it.addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT))
                    }
                    .build())
                .build()
                .create(Api::class.java)
        }
    }

}