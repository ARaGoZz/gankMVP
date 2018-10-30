package com.gank.zz.gankmvp.adapter.homeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.BaseViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactory
import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactoryList
import com.gank.zz.gankmvp.mvp.model.TodayData
import java.util.*


/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 首页RecyclerView的Adapter
 */

class HomeAdapter(data: MutableList<Visitable>) : BaseQuickAdapter<Visitable, BaseViewHolder<Visitable>>(data) {
    private val typeFactory: TypeFactory = TypeFactoryList()
    override fun convert(helper: BaseViewHolder<Visitable>, item: Visitable) {
        with(helper) {
            setUpView(data[adapterPosition], adapterPosition, this@HomeAdapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Visitable> {
        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return typeFactory.createViewHolder(viewType, itemView)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type(typeFactory)
    }
}

//首页banner图
class HomeBannerViewHolder(view: View) : BaseViewHolder<TodayData.HomeBanner>(view) {
    override fun setUpView(model: TodayData.HomeBanner, position: Int, adapter: HomeAdapter) {
        val bgaBanner: BGABanner = getView(R.id.banner)
        bgaBanner.setAdapter { banner, view, model, position ->
            Glide.with(itemView.context)
                    .load(model)
                    .into(banner.getItemImageView(position))
        }
        bgaBanner.setData(Arrays.asList(model.image1,model.image2,model.image3,model.image4,model.image5),Arrays.asList())
    }
}

//首页类型菜单栏
class HomeTypeViewHolder(view: View) : BaseViewHolder<TodayData.HomeType>(view) {
    override fun setUpView(model: TodayData.HomeType, position: Int, adapter: HomeAdapter) {
    }
}
