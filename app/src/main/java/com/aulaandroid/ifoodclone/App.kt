package com.aulaandroid.ifoodclone

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import java.lang.IllegalStateException
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.messaging.FirebaseMessaging


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get FCM registration token
            val token = task.result
            if (token != null) {
                Log.i("FIREBASE", "fcm tokem$token")
            }
        })
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