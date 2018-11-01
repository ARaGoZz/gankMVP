package com.gank.zz.gankmvp

import android.app.Application

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/01
 * desc   : app
 */

class MyApp : Application() {
    companion object {
        private lateinit var app: MyApp
        fun getApp(): MyApp {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}