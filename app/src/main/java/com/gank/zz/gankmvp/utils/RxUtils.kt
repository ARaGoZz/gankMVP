package com.gank.zz.gankmvp.utils

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.v4.content.FileProvider
import android.util.Log
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.IOException

/**
 * author : zhou
 * e-mail : aragozz@163.com
 * time   : 2018/09/25
 * desc   : Rx工具类
 */
open class RxUtils {

    companion object {
        /**
         * 线程转换
         */
        fun <T> schedulersTransformer(): ObservableTransformer<T, T> =
                ObservableTransformer {
                    it.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }

        fun getUriForFile(context: Context, file: File): Uri {
            return if (Build.VERSION.SDK_INT >= 24) getUriForFile24(context, file) else Uri.fromFile(file)
        }

        private fun getUriForFile24(context: Context, file: File): Uri {
            Log.i("pagename", context.packageName)
            return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
        }

        /**
         * 缓存文件
         *
         * @param context
         * @param filename
         * @return
         */
        fun getCacheSaveFile(context: Context, filename: String): File {
            val file: File = if (hasSDCard())
                File(context.externalCacheDir, filename)
            else
                File(context.cacheDir, filename)
            if (file.exists()) file.delete()
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return file
        }

        /**
         * 是否有SD卡
         *
         * @return
         */
        private fun hasSDCard(): Boolean {
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }
    }
}