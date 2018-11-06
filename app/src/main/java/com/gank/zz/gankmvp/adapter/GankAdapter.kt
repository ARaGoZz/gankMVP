package com.gank.zz.gankmvp.adapter

import android.content.Intent
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.mvp.model.GankData
import com.gank.zz.gankmvp.mvp.ui.activity.WebActivity

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/02
 * desc   :
 */

class GankAdapter(layoutResId: Int, data: MutableList<GankData.Result>) : BaseQuickAdapter<GankData.Result, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: GankData.Result) {
        with(helper) {
            setText(R.id.tvContent, item.desc)
            setText(R.id.tvTime, item.publishedAt)
            getView<LinearLayout>(R.id.llContent)
                    .setOnClickListener {
                        val intent = Intent(itemView.context, WebActivity::class.java)
                        intent.putExtra("title", item.desc)
                        intent.putExtra("url", item.url)
                        itemView.context.startActivity(intent)
                    }
        }
    }
}