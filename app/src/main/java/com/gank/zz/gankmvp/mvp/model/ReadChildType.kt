package com.gank.zz.gankmvp.mvp.model

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   :
 */


data class ReadChildType(
        val error: Boolean,
        val results: List<Result>
) {

    data class Result(
            val _id: String,
            val created_at: String,
            val icon: String,
            val id: String,
            val title: String
    )
}