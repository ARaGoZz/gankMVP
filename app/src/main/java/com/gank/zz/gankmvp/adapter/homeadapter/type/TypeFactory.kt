package com.gank.zz.gankmvp.adapter.homeadapter.type

import android.view.View
import com.gank.zz.gankmvp.adapter.BaseViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.mvp.model.TodayData

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/30
 * desc   :
 */

interface TypeFactory {
    //首页轮播图
    fun type(results: TodayData.HomeBanner): Int
    //首页各种类型
    fun type(type: TodayData.HomeType): Int

    fun createViewHolder(type: Int, itemView: View): BaseViewHolder<Visitable>

}