package com.gank.zz.gankmvp.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.mvp.model.GankData

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/02
 * desc   :
 */

class WelFareAdapter(layoutResId: Int, data: MutableList<GankData.Result>) : BaseQuickAdapter<GankData.Result, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: GankData.Result) {
        Glide.with(mContext)
                .load(item.url)
                .into(helper.getView(R.id.ivGirls))
    }
}