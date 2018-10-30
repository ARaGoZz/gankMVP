package com.gank.zz.gankmvp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : Fragment和ViewPager的Adapter
 */

class MyViewPagerAdapter(fm: FragmentManager?,val list: List<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return list[p0]
    }

    override fun getCount(): Int {
        return list.size
    }
}