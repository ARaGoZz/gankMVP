package com.gank.zz.gankmvp.mvp.presenter

import com.gank.zz.gankmvp.base.BasePresenter
import com.gank.zz.gankmvp.http.RetrofitClient
import com.gank.zz.gankmvp.mvp.contract.DiscoverContract
import com.gank.zz.gankmvp.utils.RxUtils
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

class DiscoverPresenter(private val view: DiscoverContract.View, lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider), DiscoverContract.Presenter {
    override fun getReadType() {
        RetrofitClient.apiService.getReadType()
                .compose(RxUtils.schedulersTransformer())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ data ->
                    view.apply {
                        getReadType(data)
                    }
                }, {})
    }

}