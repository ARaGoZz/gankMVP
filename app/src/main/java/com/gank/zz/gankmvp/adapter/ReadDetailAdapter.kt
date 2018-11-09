package com.gank.zz.gankmvp.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.mvp.model.ReadData

class ReadDetailAdapter(layoutResId: Int) : BaseQuickAdapter<ReadData.Result, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ReadData.Result) {
        with(helper){
            setText(R.id.tvReadTitle,item.title)
            Glide.with(mContext)
                    .load(item.site.icon)
                    .into(getView(R.id.ivReadTitle))
            addOnClickListener(R.id.llRead)
        }
    }
}