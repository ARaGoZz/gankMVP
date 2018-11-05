package com.gank.zz.gankmvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gank.zz.gankmvp.MyApp
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : fragment基类
 */

abstract class BaseFragment : RxFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MyApp.getRefWatcher(it)?.watch(activity) }

    }
}