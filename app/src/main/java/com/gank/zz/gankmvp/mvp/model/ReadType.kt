package com.gank.zz.gankmvp.mvp.model

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : 闲读主分类
 */


data class ReadType(
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