package com.gank.zz.gankmvp

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import java.io.BufferedReader
import android.text.TextUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.io.FileReader
import java.io.IOException
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader


/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/01
 * desc   : app
 */

class MyApp : Application() {
    private var refWatcher: RefWatcher? = null

    companion object {
        private lateinit var app: MyApp
        fun getApp(): MyApp {
            return app
        }
        fun initSmart(){
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }

        fun getRefWatcher(context: Context): RefWatcher? {
            return app.refWatcher
        }


    }
    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }


    override fun onCreate() {
        super.onCreate()
        app = this
        //leakCanary
        refWatcher = setupLeakCanary()
        //
        initSmart()
        val context = applicationContext
// 获取当前包名
        val packageName = context.packageName
// 获取当前进程名
        val processName = getProcessName(android.os.Process.myPid())
// 设置是否为上报进程
        val strategy = UserStrategy(context)
        strategy.isUploadProcess = processName == null || processName == packageName
//        CrashReport.initCrashReport(applicationContext, "64855beefb", true)
        // 初始化Bugly
        CrashReport.initCrashReport(context, "64855beefb", true, strategy)
    }

    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }
}

