package com.gank.zz.gankmvp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/06
 * desc   : 发现页面的adapter
 */
class TabAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>, private val title: List<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    //显示tab标题
    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }


}
