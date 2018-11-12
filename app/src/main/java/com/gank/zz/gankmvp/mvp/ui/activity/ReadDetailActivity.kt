package com.gank.zz.gankmvp.mvp.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.ReadDetailAdapter
import com.gank.zz.gankmvp.base.BaseActivity
import com.gank.zz.gankmvp.mvp.contract.ReadDetailContract
import com.gank.zz.gankmvp.mvp.model.ReadData
import com.gank.zz.gankmvp.mvp.presenter.ReadDetailPresenter
import kotlinx.android.synthetic.main.fragment_read_detail.*

class ReadDetailActivity : BaseActivity(), ReadDetailContract.View {
    private val count: Int = 20
    private var page: Int = 1
    private var list = ArrayList<ReadData.Result>()
    private val presenter by lazy { ReadDetailPresenter(this, this) }
    private val adapter by lazy { ReadDetailAdapter(R.layout.item_read) }
    override fun getReadData(data: ReadData) {
        list = data.results as ArrayList<ReadData.Result>
        mStatusView?.showContent()
        when (page) {
            1 -> {
                adapter.setNewData(data.results)
                refreshLayout.finishRefresh()
            }
            else -> {
                adapter.addData(data.results)
                refreshLayout.finishLoadMore()
            }
        }
    }

    override fun showLoading() {
        //加载数据的动画
        mStatusView?.showLoading()
    }

    override fun dismissLoading() {

    }

    override fun layoutId(): Int {
        return R.layout.fragment_read_detail
    }

    override fun initView() {
        mStatusView = multipleStatusView
        ibBack.setOnClickListener {
            finish()
        }
        rvReadDetail.layoutManager = LinearLayoutManager(this)
        rvReadDetail.adapter = adapter

        //上拉刷新
        refreshLayout.setOnRefreshListener {
            page = 1
            getPresenter()
        }
        //下拉加载
        refreshLayout.setOnLoadMoreListener {
            page++
            getPresenter()
        }

        adapter.setOnItemChildClickListener { _, _, position ->
            val intent = Intent(this, WebActivity::class.java)
            intent.putExtra("title", list[position].title)
            intent.putExtra("url", list[position].url)
            startActivity(intent)
        }
    }

    override fun initData() {
        getPresenter()
        tvTitle.text = intent.getStringExtra("title")
    }

    private fun getPresenter() {
        presenter.getReadData(intent.getStringExtra("id"), count, page)
    }
}