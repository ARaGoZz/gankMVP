package com.gank.zz.gankmvp.mvp.contract

import com.gank.zz.gankmvp.base.IBaseView
import com.gank.zz.gankmvp.mvp.model.GankData

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/02
 * desc   :
 */

interface GankContract {
    interface View : IBaseView {
        fun getGankData(data: GankData)
    }

    interface Presenter {
        fun getGankData(type: String, pageCount: Int, page: Int)
    }
}