package com.gank.zz.gankmvp.mvp.ui.activity

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

    override fun initView() {
        ivBack.setOnClickListener { finish() }
        tvTitle.text = intent.getStringExtra("title")

    }

    override fun initData() {
        web.loadUrl(intent.getStringExtra("url"))
    }
}