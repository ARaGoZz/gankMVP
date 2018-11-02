package com.gank.zz.gankmvp.adapter.homeadapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.BaseViewHolder
import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactory
import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactoryList
import com.gank.zz.gankmvp.mvp.model.Gank
import com.gank.zz.gankmvp.mvp.model.TodayData
import com.gank.zz.gankmvp.mvp.view.activity.GankActivity
import com.gank.zz.gankmvp.mvp.view.activity.WebActivity
import com.gank.zz.gankmvp.utils.ToastUtils
import java.util.*
import kotlin.collections.ArrayList

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 首页RecyclerView的Adapter
 *          因为使用的是BRVAH，又自己封装了一点方法，所以这个adapter有点奇怪
 */
class HomeAdapter : BaseQuickAdapter<Visitable, BaseViewHolder<Visitable>>(0) {
    private val typeFactory: TypeFactory = TypeFactoryList()
    private val dataAll = ArrayList<Visitable>()

    init {
        //给mData赋值
        mData = dataAll
    }

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

    //按照自己想要的顺序进行添加
    fun upData(data: TodayData) {
        dataAll.clear()
        val homeType = TodayData.HomeType(data.category)
        val homeBanner = TodayData.HomeBanner()
        dataAll.add(homeBanner)
        dataAll.add(homeType)
        //数据处理
        fixData(data.results)
        //数据给完刷新
        notifyDataSetChanged()
    }

    private fun fixData(results: TodayData.Results) {
        /**
         *  数据处理，变成自己想要的格式 Android App iOS restVideo expand recommend welfare
         */
        addItem(results.Android)
        addItem(results.iOS)
        addItem(results.iOS)
        addItem(results.restVideo)
        addItem(results.expand)
        addItem(results.recommend)
    }

    private fun addItem(items: List<Gank>) {
        /**
         *  indices 可以得到所得对象的下标值,将每组类型的第一个变成标题
         */
        for (i in items.indices) {
            when (i) {
                0 -> {
                    dataAll.add(TodayData.HomeGankTitle(items[i].type))
                    dataAll.add(TodayData.HomeGankList(items[i]))
                }
                else -> dataAll.add(TodayData.HomeGankList(items[i]))
            }
        }
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
        bgaBanner.setData(Arrays.asList(model.image1, model.image2, model.image3, model.image4, model.image5), Arrays.asList())
    }
}

//首页类型菜单栏
class HomeTypeViewHolder(view: View) : BaseViewHolder<TodayData.HomeType>(view) {
    override fun setUpView(model: TodayData.HomeType, position: Int, adapter: HomeAdapter) {
        getView<LinearLayout>(R.id.llWelfare).setOnClickListener {
            toActivity("福利")
        }
        getView<LinearLayout>(R.id.llAndroid).setOnClickListener {
            toActivity("Android")
        }
        getView<LinearLayout>(R.id.llIOS).setOnClickListener {
            toActivity("iOS")
        }
        getView<LinearLayout>(R.id.llExpand).setOnClickListener {
            toActivity("拓展资源")
        }
        getView<LinearLayout>(R.id.llRecommend).setOnClickListener {
            toActivity("瞎推荐")
        }
        getView<LinearLayout>(R.id.llVideo).setOnClickListener {
            toActivity("休息视频")
        }
    }

    private fun toActivity(title: String) {
        val intent = Intent(itemView.context, GankActivity::class.java)
        intent.putExtra("title", title)
        itemView.context.startActivity(intent)
    }
}

//首页标题
class HomeGankTitleViewHolder(view: View) : BaseViewHolder<TodayData.HomeGankTitle>(view) {
    override fun setUpView(model: TodayData.HomeGankTitle, position: Int, adapter: HomeAdapter) {
        setText(R.id.tvTitle, model.title)
    }
}

//首页gank数据
class HomeGankListViewHolder(view: View) : BaseViewHolder<TodayData.HomeGankList>(view) {
    override fun setUpView(model: TodayData.HomeGankList, position: Int, adapter: HomeAdapter) {
        setText(R.id.tvContent, model.gankList.desc)
        getView<LinearLayout>(R.id.llContent)
                .setOnClickListener {
                    val intent = Intent(itemView.context, WebActivity::class.java)
                    intent.putExtra("title", model.gankList.desc)
                    intent.putExtra("url",model.gankList.url)
                    itemView.context.startActivity(intent)
                }
        //都是动态图，不加载了
//        val imageView = getView<ImageView>(R.id.ivContent)
//        val imageUrl = model.gankList.images?.let {
//            if (it.isNotEmpty()) {
//                it[0].toString()
//            } else {
//                ""
//            }
//        }
//        Glide.with(itemView.context)
//                .load(imageUrl)
//                .into(imageView)
//        if (imageUrl.isNullOrEmpty()) {
//            imageView.visibility = View.GONE
//        } else {
//            imageView.visibility = View.VISIBLE
//        }
    }
}
