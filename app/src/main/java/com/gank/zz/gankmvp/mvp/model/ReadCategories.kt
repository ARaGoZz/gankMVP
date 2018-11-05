package com.gank.zz.gankmvp.mvp.model

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/05
 * desc   :
 */

data class ReadCategories(
        val error: Boolean,
        val results: List<Result>
) {

    data class Result(
            val _id: String,
            val en_name: String,
            val name: String,
            val rank: Int
    )
}