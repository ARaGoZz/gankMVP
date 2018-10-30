package com.gank.zz.gankmvp.http

import com.gank.zz.gankmvp.api.ApiService
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : Retrofit封装
 */

object RetrofitClient {
    //超时时间
    private const val HTTP_TIMEOUT = 15L

    val apiService: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = LoggingInterceptor
                .Builder()//构建者模式
                .loggable(true) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
//                        .addHeader("log-header", "I am the log request header.") // 添加请求头, 注意 key 和 value 都不能是中文
                .build()
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }
}