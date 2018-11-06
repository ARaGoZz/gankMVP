package com.gank.zz.gankmvp.mvp.contract

import com.gank.zz.gankmvp.base.IBaseView
import com.gank.zz.gankmvp.mvp.model.ReadChildType

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

interface ReadContract{
    interface View : IBaseView{
        //获取闲读的子分类
        fun getReadChildType(data: ReadChildType)
    }
    interface Presenter {
        fun getReadChildType(type: String)
    }
}