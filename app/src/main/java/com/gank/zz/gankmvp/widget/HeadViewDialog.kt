package com.gank.zz.gankmvp.widget

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.gank.zz.gankmvp.R
import kotlinx.android.synthetic.main.dialog_photo.view.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/11/13
 * desc   :
 */

class HeadViewDialog : DialogFragment() {
    private var listener: OnHeadViewListener? = null

    interface OnHeadViewListener {
        fun onCamera()

        fun onAlbum()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, R.style.normal_dialog)
    }

    fun setOnHeadViewListener(headViewListener: OnHeadViewListener) {
        listener = headViewListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_photo, null)
        val window = dialog.window
//        window.decorView.setPadding(0, 0, 0, 0)
        val params = window.attributes
        //底部显示
        params.gravity = Gravity.BOTTOM
        params.width = WindowManager.LayoutParams.MATCH_PARENT
//        params.height = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = params
        init(view)
        return view
    }


    private fun init(view: View) {
        view.tvAlbum.setOnClickListener {
            listener?.onAlbum()
        }
        view.tvCamera.setOnClickListener {
            listener?.onCamera()
        }

    }

}