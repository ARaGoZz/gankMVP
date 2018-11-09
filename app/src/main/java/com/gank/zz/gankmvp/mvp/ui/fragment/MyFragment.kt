package com.gank.zz.gankmvp.mvp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.utils.PicUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.fragment_my.*

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 我的
 */

class MyFragment : BaseFragment() {
    //得到的图片uri
    private var imageUri: Uri? = null

    companion object {
        const val CHOOSE_ALBUM = 7777
    }

    override fun lazyLoad() {
    }

    override fun layoutId(): Int = R.layout.fragment_my

    override fun initView() {
        ivHead.setOnClickListener {
            //申请权限
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.READ_EXTERNAL_STORAGE)
                    .onGranted {
                        Log.i("permission", "允许权限")
                        //访问相册
                        choseFromAlbum()
                    }
                    .onDenied {
                        Log.i("permission", "拒绝权限")

                    }
                    .start()
        }
    }

    private fun choseFromAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOOSE_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CHOOSE_ALBUM -> {
                Log.i("permission", "${data?.data}")
                imageUri = data?.data
                //适配用的
//                val realPath = PicUtils.getPath(activity, imageUri)
                //这就完了？
                Glide.with(this)
                        .load(imageUri)
                        .into(ivHead)
            }
        }
    }

}