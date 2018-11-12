package com.gank.zz.gankmvp.mvp.ui.fragment

import android.support.v4.app.Fragment
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.TabAdapter
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.mvp.contract.DiscoverContract
import com.gank.zz.gankmvp.mvp.model.ReadType
import com.gank.zz.gankmvp.mvp.presenter.DiscoverPresenter
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 发现
 */

class DiscoverFragment : BaseFragment(), DiscoverContract.View {
    override fun lazyLoad() {
        presenter.getReadType()
    }

    private val presenter by lazy { DiscoverPresenter(this, this) }
    private val tabTitleList = ArrayList<String>()
    private val fragmentList = ArrayList<Fragment>()
    override fun getReadType(data: ReadType) {
        data.results.mapTo(tabTitleList) { it.name }
        data.results.mapTo(fragmentList) { ReadFragment.getInstance(it.en_name) }
        vpRead.adapter = TabAdapter(childFragmentManager, fragmentList, tabTitleList)
        tbRead.setupWithViewPager(vpRead)
        vpRead.offscreenPageLimit = fragmentList.size
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun layoutId(): Int = R.layout.fragment_discover

    override fun initView() {
    }


}