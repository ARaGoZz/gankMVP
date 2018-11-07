package com.gank.zz.gankmvp.mvp.presenter

import com.gank.zz.gankmvp.base.BasePresenter
import com.gank.zz.gankmvp.http.RetrofitClient
import com.gank.zz.gankmvp.mvp.contract.HomeContract
import com.gank.zz.gankmvp.utils.RxUtils
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 首页的请求处理
 */

class HomePresenter(val view: HomeContract.View, lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider), HomeContract.Presenter {
    override fun getTodayData() {
        view.showLoading()
        RetrofitClient.apiService.getTodayData()
                .compose(RxUtils.schedulersTransformer())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ data ->
                    view.apply {
                        getTodayData(data)
                    }
                }, {

                })

    }
}