package com.aulaandroid.ifoodclone.network

import android.content.Intent
import android.util.Log
import com.aulaandroid.ifoodclone.MainActivity
import com.aulaandroid.ifoodclone.PreferenceHelper
import com.aulaandroid.ifoodclone.R
import com.aulaandroid.ifoodclone.common.NotificationUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService : FirebaseMessagingService() {
    val TAG = "firebase"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")
        PreferenceHelper.setString("FB_TOKEN", token)
    }

    // recebe a notificação de push
    override fun onMessageReceived(mensagemRemota: RemoteMessage) {
        Log.d(TAG, "onMessageReceived")

        // verifica se a mensagem recebida do firebase é uma notificação
        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val corpo = mensagemRemota?.notification?.body
            Log.d(TAG, "Titulo da mensagem: $titulo")
            Log.d(TAG, "Corpo da mensagem: $corpo")

            showNotification(mensagemRemota)
        }
    }

    private fun showNotification(remoteMsg: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        val title = remoteMsg?.notification?.title ?: getString(R.string.app_name)
        var message = remoteMsg?.notification?.body!!

        if (remoteMsg?.data.isNotEmpty()) {
            val productId = remoteMsg.data["productId"]?.toLong()!!
            message += ""
            val product = ProductService.getProduct(productId)
            intent.putExtra("product", product)
        }
        NotificationUtil.create(this, 1, intent, title, message)
    }
}