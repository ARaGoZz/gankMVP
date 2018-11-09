package com.gank.zz.gankmvp.mvp.presenter

import com.gank.zz.gankmvp.base.BasePresenter
import com.gank.zz.gankmvp.http.RetrofitClient
import com.gank.zz.gankmvp.mvp.contract.ReadDetailContract
import com.gank.zz.gankmvp.utils.RxUtils
import com.trello.rxlifecycle2.LifecycleProvider

class ReadDetailPresenter(private val view: ReadDetailContract.View,lifecycleProvider: LifecycleProvider<*>) : BasePresenter(lifecycleProvider),ReadDetailContract.Presenter {
    override fun getReadData(id: String, count: Int, page: Int) {
        view.showLoading()
        RetrofitClient.apiService.getReadData(id,count,page)
                .compose(RxUtils.schedulersTransformer())
                .compose(lifecycleProvider.bindToLifecycle())
                .subscribe({data ->
                    view.apply {
                        getReadData(data)
                    }
                },{})
    }
}