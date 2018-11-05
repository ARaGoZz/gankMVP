package com.gank.zz.gankmvp.base

import android.os.Bundle
import com.gank.zz.gankmvp.MyApp
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : Activity基类
 */
//使用rxLifecycle管理生命周期
abstract class BaseActivity : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        MyApp.getRefWatcher(this)?.watch(this)
    }
}
