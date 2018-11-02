package com.gank.zz.gankmvp.mvp.presenter

import com.gank.zz.gankmvp.base.BasePresenter
import com.gank.zz.gankmvp.http.RetrofitClient
import com.gank.zz.gankmvp.mvp.contract.GankContract
import com.gank.zz.gankmvp.utils.RxUtils
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/02
 * desc   :
 */

class GankPresenter(private val view: GankContract.View, lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider), GankContract.Presenter {
    override fun getGankData(type: String, pageCount: Int, page: Int) {
        RetrofitClient.apiService.getGankData(type, pageCount, page)
                .compose(RxUtils.schedulersTransformer())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ data ->
                    view.apply {
                        getGankData(data)
                    }
                }, {})
    }
}