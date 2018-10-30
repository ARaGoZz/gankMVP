package com.gank.zz.gankmvp.adapter.homeadapter.model

import com.gank.zz.gankmvp.adapter.homeadapter.type.TypeFactory

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/30
 * desc   :
 */

interface Visitable{
    fun type(typeFactory: TypeFactory):Int
}