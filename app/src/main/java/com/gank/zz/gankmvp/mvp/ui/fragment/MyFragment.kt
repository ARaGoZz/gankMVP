package com.gank.zz.gankmvp.mvp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import com.gank.zz.gankmvp.MyApp
import com.gank.zz.gankmvp.R
import com.gank.zz.gankmvp.base.BaseFragment
import com.gank.zz.gankmvp.utils.RxUtils
import com.gank.zz.gankmvp.widget.HeadViewDialog
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.fragment_my.*
import java.io.File

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/10/26
 * desc   : 我的
 */

class MyFragment : BaseFragment() {
    //得到的图片uri
    private var imageUri: Uri? = null

    private var imageFile: File? = null

    companion object {
        const val CHOOSE_ALBUM = 7777
        const val TAKE_PHOTO = 6666
    }

    override fun lazyLoad() {
    }

    override fun layoutId(): Int = R.layout.fragment_my

    override fun initView() {
        ivHead.setOnClickListener {
            val dialog = HeadViewDialog()
            dialog.setOnHeadViewListener(object : HeadViewDialog.OnHeadViewListener {
                override fun onCamera() {
                    dialog.dismiss()
                    initPermissionCamera()

                }

                override fun onAlbum() {
                    dialog.dismiss()
                    initPermissionAlbum()
                }
            })
            dialog.show(fragmentManager, "headView")
        }
    }

    private fun initPermissionAlbum() {
        //申请权限
        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE
                )
                .onGranted {
                    Log.i("permission", "允许权限")
                    //访问照相机
                    choseFromAlbum()
                }
                .onDenied {
                    Log.i("permission", "拒绝权限")

                }
                .start()
    }

    private fun initPermissionCamera() {
        //申请权限
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CAMERA,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted {
                    Log.i("permission", "允许权限")
                    //访问相册
                    startCamera()
                }
                .onDenied {
                    Log.i("permission", "拒绝权限")

                }
                .start()
    }

    //打开相册
    private fun choseFromAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, CHOOSE_ALBUM)
    }

    //打开照相机
    private fun startCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
//        imageFile = File(Environment.getExternalStorageDirectory().absolutePath + "/test/" + System.currentTimeMillis() + ".jpg")
        imageFile = RxUtils.getCacheSaveFile(MyApp.getApp(),"/test/" + System.currentTimeMillis() + ".jpg")
        //需要文件读写权限
//        imageFile?.parentFile?.mkdir()
        //7.0以前的做法,会抛出FileUriExposedException
//        imageUri = Uri.fromFile(imageFile)
        //改变Uri
        imageUri = RxUtils.getUriForFile(MyApp.getApp(),imageFile!!)

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        startActivityForResult(intent, TAKE_PHOTO)
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
            TAKE_PHOTO -> {
                Log.i("permission", "$imageUri")

                Glide.with(this)
                        .load(imageUri)
                        .into(ivHead)
            }
        }
    }

}