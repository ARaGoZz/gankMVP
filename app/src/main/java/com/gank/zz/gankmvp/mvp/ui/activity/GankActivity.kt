package com.gank.zz.gankmvp.mvp.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.GankAdapter
import com.gank.zz.gankmvp.adapter.WelFareAdapter
import com.gank.zz.gankmvp.base.BaseActivity
import com.gank.zz.gankmvp.mvp.contract.GankContract
import com.gank.zz.gankmvp.mvp.model.GankData
import com.gank.zz.gankmvp.mvp.presenter.GankPresenter
import kotlinx.android.synthetic.main.activity_gank.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/01
 * desc   : 各种gank列表展示页
 */

class GankActivity : BaseActivity(), GankContract.View {
    private val pageCount = 20
    private var page = 1
    private val presenter by lazy { GankPresenter(this, this) }
    private var list = ArrayList<GankData.Result>()
    private val adapter by lazy { GankAdapter(R.layout.item_gank, list) }
    private val girlsAdapter by lazy { WelFareAdapter(R.layout.item_girls, list) }
    override fun getGankData(data: GankData) {
        list = data.results as ArrayList<GankData.Result>
        if (tvTitle.text != "福利") {
            when (page) {
                1 -> {
                    adapter.setNewData(list)
                    refreshLayout.finishRefresh()
                }
                else -> {
                    adapter.addData(list)
                    refreshLayout.finishLoadMore()
                }
            }
        } else {
            when (page) {
                1 -> {
                    girlsAdapter.setNewData(list)
                    refreshLayout.finishRefresh()
                }
                else -> {
                    girlsAdapter.addData(list)
                    refreshLayout.finishLoadMore()
                }
            }
        }
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_gank
    }

    override fun initView() {
        ivBack.setOnClickListener { finish() }
        tvTitle.text = intent.getStringExtra("title")
        rvGank.layoutManager = LinearLayoutManager(this)
        if (tvTitle.text != "福利") {
            rvGank.adapter = adapter
        } else {
            rvGank.adapter = girlsAdapter
        }
        //上拉刷新
        refreshLayout.setOnRefreshListener {
            page = 1
            presenter.getGankData(tvTitle.text.toString(), pageCount, page)
        }
        //下拉加载
        refreshLayout.setOnLoadMoreListener {
            page++
            presenter.getGankData(tvTitle.text.toString(), pageCount, page)
        }
    }

    override fun initData() {
        presenter.getGankData(tvTitle.text.toString(), pageCount, page)
    }
}