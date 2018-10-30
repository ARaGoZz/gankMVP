package com.gank.zz.gankmvp.mvp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.homeadapter.HomeAdapter
import com.gank.zz.gankmvp.adapter.homeadapter.model.Visitable
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.mvp.contract.HomeContract
import com.gank.zz.gankmvp.mvp.model.TodayData
import com.gank.zz.gankmvp.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 主页
 */

class HomeFragment : BaseFragment(),HomeContract.View{
    private val dataAll = ArrayList<Visitable>()
    private val adapter by lazy { HomeAdapter(dataAll) }
    override fun getTodayData(data: TodayData) {
        dataAll.clear()
        val homeType = TodayData.HomeType(data.category)
        val homeBanner = TodayData.HomeBanner()
        dataAll.add(homeBanner)
        dataAll.add(homeType)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private val presenter by lazy { HomePresenter(this,this) }

    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView() {
    }

    override fun initData() {
        //放在onCreate请求可以在onDestroy内自动取消订阅
        presenter.getTodayData()
        rvHome.layoutManager = LinearLayoutManager(activity)
        rvHome.adapter = adapter
    }

}