package com.gank.zz.gankmvp.mvp.contract

import com.gank.zz.gankmvp.base.IBaseView
import com.gank.zz.gankmvp.mvp.model.ReadType

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

interface DiscoverContract {
    interface View : IBaseView {
        //获取闲读的主分类
        fun getReadType(data: ReadType)
    }

    interface Presenter {
        fun getReadType()
    }
}