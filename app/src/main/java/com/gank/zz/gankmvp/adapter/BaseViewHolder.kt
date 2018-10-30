package com.gank.zz.gankmvp.adapter

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.HomeAdapter

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   :
 */

abstract class BaseViewHolder<out T>(view: View) : BaseViewHolder(view) {
    abstract fun setUpView(model: @UnsafeVariance T, position: Int, adapter: HomeAdapter)
}