package com.gank.zz.gankmvp.mvp.view.activity

import android.support.v4.app.Fragment
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.adapter.MyViewPagerAdapter
import com.gank.zz.gankmvp.base.BaseActivity
import com.gank.zz.gankmvp.mvp.view.fragment.DiscoverFragment
import com.gank.zz.gankmvp.mvp.view.fragment.HomeFragment
import com.gank.zz.gankmvp.mvp.view.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.majiajie.pagerbottomtabstrip.NavigationController
import java.util.ArrayList

class MainActivity : BaseActivity() {
    private val homeFragment by lazy { HomeFragment() }
    private val discoverFragment by lazy { DiscoverFragment() }
    private val myFragment by lazy { MyFragment() }
    private var mList: MutableList<Fragment> = ArrayList()
    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() {
        val bottomBar: NavigationController = mBottomNavigation.material()
                .addItem(R.mipmap.home, "主页")
                .addItem(R.mipmap.all, "发现")
                .addItem(R.mipmap.my, "我的")
                .build()

        initFragment()

        NSViewPager.adapter = MyViewPagerAdapter(supportFragmentManager,mList)
        //自动适配ViewPager页面切换
        bottomBar.setupWithViewPager(NSViewPager)
    }

    private fun initFragment() {
        mList.clear()
        mList.add(homeFragment)
        mList.add(discoverFragment)
        mList.add(myFragment)
    }

    override fun initData() {
    }
}
