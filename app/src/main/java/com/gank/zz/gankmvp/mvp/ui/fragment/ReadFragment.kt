package com.gank.zz.gankmvp.mvp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.ReadAdapter
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.mvp.contract.ReadContract
import com.gank.zz.gankmvp.mvp.model.ReadChildType
import com.gank.zz.gankmvp.mvp.presenter.ReadPresenter
import kotlinx.android.synthetic.main.fragment_read.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   :
 */

class ReadFragment : BaseFragment(), ReadContract.View {
    override fun lazyLoad() {
        if (!category.isNullOrEmpty()) {
            presenter.getReadChildType(category)
        }
    }

    private val presenter by lazy { ReadPresenter(this, this) }
    private var adapter: ReadAdapter? = null
    private var list = ArrayList<ReadChildType.Result>()
    override fun getReadChildType(data: ReadChildType) {
        list = data.results as ArrayList<ReadChildType.Result>
        activity?.let {
            rvRead.layoutManager = LinearLayoutManager(activity)
            adapter = ReadAdapter(R.layout.item_read, list)
            rvRead.adapter = adapter
        }

    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    private lateinit var category: String

    companion object {
        fun getInstance(category: String): ReadFragment {
            val fragment = ReadFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.category = category
            return fragment
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_read
    }

    override fun initView() {
    }

}