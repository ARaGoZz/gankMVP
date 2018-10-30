package com.gank.zz.gankmvp.base

import com.trello.rxlifecycle2.LifecycleProvider

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/25
 * desc   : 在presenter里面处理订阅
 */

abstract class BasePresenter(var lifecycleProvider: LifecycleProvider<*>)