package com.gank.zz.gankmvp.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.mvp.model.ReadChildType

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

class ReadAdapter(layoutResId: Int, data: MutableList<ReadChildType.Result>?) : BaseQuickAdapter<ReadChildType.Result, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: ReadChildType.Result) {
        with(helper) {
            setText(R.id.tvReadTitle, item.title)
            Glide.with(mContext)
                    .load(item.icon)
                    .into(getView(R.id.ivReadTitle))
        }
    }
}