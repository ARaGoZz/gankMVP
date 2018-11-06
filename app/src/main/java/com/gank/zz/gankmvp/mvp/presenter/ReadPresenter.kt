package com.gank.zz.gankmvp.mvp.presenter

import com.gank.zz.gankmvp.base.BasePresenter
import com.gank.zz.gankmvp.http.RetrofitClient
import com.gank.zz.gankmvp.mvp.contract.ReadContract
import com.gank.zz.gankmvp.utils.RxUtils
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

class ReadPresenter(private val view: ReadContract.View, lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider), ReadContract.Presenter {

    override fun getReadChildType(type: String) {
        RetrofitClient.apiService.getReadChildType(type)
                .compose(RxUtils.schedulersTransformer())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({ data ->
                    view.apply {
                        getReadChildType(data)
                    }
                }, {})
    }
}