package com.gank.zz.gankmvp.api

import com.gank.zz.gankmvp.mvp.model.ReadChildType
import com.gank.zz.gankmvp.mvp.model.ReadData
import com.gank.zz.gankmvp.mvp.model.ReadType
import com.gank.zz.gankmvp.mvp.model.TodayData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : api接口信息
 */

interface ApiService {
    companion object {
        const val BASE_URL = "https://gank.io/api/"
    }

    //获取最新一天的干货
    @GET("today")
    fun getTodayData(): Observable<TodayData>

    //获取闲读的主分类
    @GET("xiandu/categories")
    fun getReadType(): Observable<ReadType>

    //获取闲读的子分类
    @GET("xiandu/category/{type}")
    fun getReadChildType(@Path("type") type: String): Observable<ReadChildType>

    //获取闲读数据
    @GET("xiandu/data/id/{id}/count/{count}/page/{page}")
    fun getReadData(@Path("id") id: String, @Path("count") count: Int, @Path("page") page: Int): Observable<ReadData>

    //分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
    @GET("data/{category}/{pageCount}/{page}")
    fun getGankData(@Path("category") category: String, @Path("pageCount") pageCount: Int, @Path("page") page: Int)

}