package com.noob.apps.mvpretrofit.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkHelper {
    companion object{
        fun isNetworkAvailable(mContext: Context): Boolean {
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}