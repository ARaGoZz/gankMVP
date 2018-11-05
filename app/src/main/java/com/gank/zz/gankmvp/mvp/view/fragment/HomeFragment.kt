package com.gank.zz.gankmvp.mvp.view.fragment

import android.support.v7.widget.LinearLayoutManager
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.homeadapter.HomeAdapter
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.mvp.contract.HomeContract
import com.gank.zz.gankmvp.mvp.model.TodayData
import com.gank.zz.gankmvp.mvp.presenter.HomePresenter
import com.gank.zz.gankmvp.utils.ToastUtils
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 主页
 */
//TODO 首页的CollapsingToolbarLayout
class HomeFragment : BaseFragment(), HomeContract.View {
    //不能通过by lazy 否则会内存泄漏
    private var adapter: HomeAdapter? = null

    override fun getTodayData(data: TodayData) {
        adapter?.upData(data)
        //等到数据刷新停止
        refreshLayout.finishRefresh()
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private val presenter by lazy { HomePresenter(this, this) }

    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView() {
        //上拉刷新
        refreshLayout.setOnRefreshListener {
            presenter.getTodayData()
        }
        //TODO 点击搜索弹出界面
        ivSearch.setOnClickListener {
            ToastUtils.makeToastShort("搜索")
        }
    }

    override fun initData() {
        //放在onCreate请求可以在onDestroy内自动取消订阅
        presenter.getTodayData()

        adapter = HomeAdapter()
        rvHome.layoutManager = LinearLayoutManager(activity)
        rvHome.adapter = adapter
//        val leakThread = LeakThread()
//        leakThread.start()
        //测试bugly
//        CrashReport.testJavaCrash()
    }

}

//class LeakThread : Thread() {
//    override fun run() {
////        try {
//            Thread.sleep(6 * 60 * 1000)
////        } catch (e: InterruptedException) {
////            e.printStackTrace()
////        }
//
//    }
//}