package com.aulaandroid.ifoodclone

import android.app.Application
import java.lang.IllegalStateException


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
//        public const val API_HOST = "http://10.0.2.2:5000"
        public const val API_HOST = "https://kalmeida.pythonanywhere.com"
        private var appInstance: App? = null
        fun getInstance(): App {
            if (appInstance == null) {
                throw IllegalStateException("Configure application correctly in android Manifest")
            }
            return appInstance!!
        }
    }
}