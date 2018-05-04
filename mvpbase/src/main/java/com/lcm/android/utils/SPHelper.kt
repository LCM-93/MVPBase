package com.lcm.android.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.util.Base64
import java.io.*

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/3 15:12
 * Desc:
 * *****************************************************************
 */
object SPHelper {

    const val SP_NAME: String = "SP_CONFIG"
    var mSharedPreferences: SharedPreferences? = null

    @JvmStatic fun setStringSF(context: Context, key: String, value: String): Unit {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences?.edit()?.putString(key, value)?.commit()
    }

    @JvmStatic fun getStringSF(context: Context, key: String): String? {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreferences?.getString(key, null)
    }

    @JvmStatic fun setIntergerSF(context: Context, key: String, value: Int): Unit {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences?.edit()?.putInt(key, value)?.commit()
    }

    @JvmStatic fun getIntergerSF(context: Context, key: String): Int? {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return mSharedPreferences?.getInt(key, -1)
    }

    @JvmStatic fun removeSF(context: Context, key: String): Unit {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences?.edit()?.remove(key)?.commit()
    }

    @JvmStatic fun clearShareprefrence(context: Context): Unit {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        mSharedPreferences?.edit()?.clear()?.commit()
    }


    /**
     * 将对象存储到sharepreference
     */
    @JvmStatic fun <T> saveDeviceData(context: Context,key: String,device:T): Boolean{
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        var baos = ByteArrayOutputStream()
        try {
            var oos:ObjectOutputStream = ObjectOutputStream(baos)
            oos.writeObject(device)
            var oAuth_Base64:String = String(Base64.encode(baos.toByteArray(),Base64.DEFAULT))
            mSharedPreferences?.edit()?.putString(key,oAuth_Base64)?.commit()
            return true
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }

    /**
     * 将对象从sharepreference中取出来
     */
//    @JvmStatic fun <T> getDeviceData(context: Context,key: String):T?{
//        if (mSharedPreferences == null) {
//            mSharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
//        }
//        var device: T?=null
//        var productBase64:String? = mSharedPreferences?.getString(key,null)
//        if(productBase64 == null) return null
//
//        var base64 = Base64.decode(productBase64.toByteArray(),Base64.DEFAULT)
//        var bais = ByteArrayInputStream(base64)
//        try {
//            var bis = ObjectInputStream(bais)
//            device = bis.readObject() as T?
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//        return device
//    }


    /**
     * 返回缓存文件夹
     */
    @JvmStatic fun getCacheFilePath(context: Context):String{
        var packageName: String = context.packageName
        return Environment.getExternalStorageDirectory().path+packageName
    }


    /**
     * 获取自定义缓存文件地址
     */
    @JvmStatic fun getCacheFile(context: Context):File{
        if (context.cacheDir==null || Environment.getExternalStorageState() == null||Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            var file:File = context.externalCacheDir
//            if (file == null){
//                file = File(getCacheFilePath(context))
//            }
            return file
        }else{
            return context.cacheDir
        }
    }




}