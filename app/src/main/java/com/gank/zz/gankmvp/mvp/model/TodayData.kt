package com.gank.zz.gankmvp.mvp.model

import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactory
import com.google.gson.annotations.SerializedName

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : Today
 */

data class TodayData(
        val category: List<String>,
        val error: Boolean,
        val results: Results
) {

    data class Results(
            val Android: List<Gank>,
            val App: List<Gank>,
            val iOS: List<Gank>,
            @SerializedName("休息视频")
            val restVideo: List<Gank>,
            @SerializedName("拓展资源")
            val expand: List<Gank>,
            @SerializedName("瞎推荐")
            val recommend: List<Gank>,
            @SerializedName("福利")
            val welfare: List<Gank>
    )

    data class HomeType(val homeType: List<String>) : Visitable {
        override fun type(typeFactory: TypeFactory): Int {
            return typeFactory.type(this)
        }
    }

    //放几张图当首页轮播图
    data class HomeBanner(
            val image1: String = "https://ws1.sinaimg.cn/large/0065oQSqly1fv5n6daacqj30sg10f1dw.jpg",
            val image2: String = "https://ws1.sinaimg.cn/large/0065oQSqly1fw8wzdua6rj30sg0yc7gp.jpg",
            val image3: String = "https://ws1.sinaimg.cn/large/0065oQSqly1fw0vdlg6xcj30j60mzdk7.jpg",
            val image4: String = "http://ww1.sinaimg.cn/large/0065oQSqly1fsfq1k9cb5j30sg0y7q61.jpg",
            val image5: String = "https://ws1.sinaimg.cn/large/0065oQSqly1fuo54a6p0uj30sg0zdqnf.jpg") : Visitable {
        override fun type(typeFactory: TypeFactory): Int {
            return typeFactory.type(this)
        }
    }

    //gank标题的数据
    data class HomeGankTitle(val title: String) : Visitable {
        override fun type(typeFactory: TypeFactory): Int {
            return typeFactory.type(this)
        }
    }

    //Gank的数据
    data class HomeGankList(val gankList: Gank) : Visitable {
        override fun type(typeFactory: TypeFactory): Int {
            return typeFactory.type(this)
        }
    }
}

data class Gank(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: List<Any>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String
)
