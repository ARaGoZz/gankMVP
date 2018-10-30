package com.gank.zz.gankmvp.adapter.homeadapter.type

import android.view.View
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.BaseViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.HomeBannerViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.HomeTypeViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.mvp.model.TodayData

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/30
 * desc   :
 */
class TypeFactoryList : TypeFactory {
    private val homeBannerType = R.layout.view_holder_home_banner
    private val homeTypeType = R.layout.view_holder_home_type


    override fun type(results: TodayData.HomeBanner): Int {
        return homeBannerType
    }

    override fun type(type: TodayData.HomeType): Int {
        return homeTypeType
    }


    override fun createViewHolder(type: Int, itemView: View): BaseViewHolder<Visitable> {
        return when (type) {
            homeBannerType -> HomeBannerViewHolder(itemView)
            homeTypeType -> HomeTypeViewHolder(itemView)
            else -> null!!
        }
    }


}
