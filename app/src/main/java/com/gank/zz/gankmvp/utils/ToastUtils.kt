package com.gank.zz.gankmvp.utils

import android.widget.Toast
import com.gank.zz.gankmvp.MyApp

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/01
 * desc   :
 */
class ToastUtils {
    companion object {
        private var toast: Toast? = null

        fun makeToastShort(content: String) {
            if (toast == null) {
                toast = Toast.makeText(MyApp.getApp(), content, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(content)
            }
            toast!!.show()
        }

        fun makeToastLong(content: String) {
            if (toast == null) {
                toast = Toast.makeText(MyApp.getApp(), content, Toast.LENGTH_LONG)
            } else {
                toast!!.setText(content)
            }
            toast!!.show()
        }
    }
}