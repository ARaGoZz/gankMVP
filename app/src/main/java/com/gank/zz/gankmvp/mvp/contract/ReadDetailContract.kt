package com.gank.zz.gankmvp.mvp.contract

import com.gank.zz.gankmvp.base.IBaseView
import com.gank.zz.gankmvp.mvp.model.ReadData

interface ReadDetailContract {
    interface View : IBaseView {
        fun getReadData(data: ReadData)
    }

    interface Presenter {
        fun getReadData(id: String, count: Int, page: Int)
    }
}