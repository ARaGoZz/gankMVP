package com.gank.zz.gankmvp.utils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/09/25
 * desc   : Rx工具类
 */
class RxUtils {

    companion object {
        /**
         * 线程转换
         */
        fun <T> schedulersTransformer(): ObservableTransformer<T, T> =
                ObservableTransformer {
                    it.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
    }
}