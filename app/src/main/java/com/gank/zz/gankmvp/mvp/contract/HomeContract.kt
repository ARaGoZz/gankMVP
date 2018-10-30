package com.gank.zz.gankmvp.mvp.contract

import com.gank.zz.gankmvp.base.IBaseView
import com.gank.zz.gankmvp.mvp.model.TodayData

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   :
 */

interface HomeContract{
    interface View: IBaseView{
        fun getTodayData(data: TodayData)
    }
    interface Presenter{
        fun getTodayData()
    }
}