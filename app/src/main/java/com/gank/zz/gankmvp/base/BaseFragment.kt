package com.gank.zz.gankmvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.gank.zz.gankmvp.MyApp
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : fragment基类
 */

abstract class BaseFragment : RxFragment() {
    companion object {
        const val GFO = 1
    }
    /**
     * 视图是否加载完毕
     */
    private var isView = false
    /**
     * 数据是否加载过了
     */
    private var isData = false

    /**
     * 切换布局状态
     */
    protected var mStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isView = true
        initView()
        lazyData()
    }

    abstract fun layoutId(): Int

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MyApp.getRefWatcher(it)?.watch(activity) }

    }

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            //加载数据
            lazyData()
        }
    }
    private fun lazyData(){
        if (userVisibleHint && isView && !isData){
            lazyLoad()
            isData = true
        }
    }
}