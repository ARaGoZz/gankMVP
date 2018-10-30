package com.gank.zz.gankmvp.mvp.model

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   :
 */

data class GankData(
        val error: Boolean,
        val results: List<Result>
) {

    data class Result(
            val _id: String,
            val createdAt: String,
            val desc: String,
            val images: List<String>,
            val publishedAt: String,
            val source: String,
            val type: String,
            val url: String,
            val used: Boolean,
            val who: String
    )
}