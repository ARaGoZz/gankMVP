package com.gank.zz.gankmvp.mvp.ui.activity

import android.annotation.SuppressLint
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/02
 * desc   :
 */

class WebActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_web
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        ivBack.setOnClickListener { finish() }
        tvTitle.text = intent.getStringExtra("title")
        with(web.settings) {
            javaScriptEnabled = true//支持js
            useWideViewPort = true//将图片调整到适合webview的大小
            loadWithOverviewMode = true// 缩放至屏幕的大小
            setSupportZoom(true)//支持缩放
            allowFileAccess = true//设置可以访问文件
            javaScriptCanOpenWindowsAutomatically = true//支持通过JS打开新窗口
            loadsImagesAutomatically = true//支持自动加载图片
            defaultTextEncodingName = "utf-8"//设置编码格式
        }
        web.loadUrl(intent.getStringExtra("url"))
    }

    override fun initData() {
    }
}