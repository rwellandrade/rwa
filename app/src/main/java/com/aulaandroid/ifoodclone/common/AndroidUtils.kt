package com.aulaandroid.ifoodclone.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.aulaandroid.ifoodclone.App

object AndroidUtils {
    fun isOnline(): Boolean {
        val conexao =
            App.getInstance().applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map { conexao.getNetworkInfo(it) }
            .any { it!!.state == NetworkInfo.State.CONNECTED }
    }
}