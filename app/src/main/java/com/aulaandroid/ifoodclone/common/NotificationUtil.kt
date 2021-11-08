package com.aulaandroid.ifoodclone.common

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.aulaandroid.ifoodclone.App
import com.aulaandroid.ifoodclone.R

object NotificationUtil {
    internal val CHANNEL_ID = "ifood_app1"
    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O)
            return;
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val appName = context.getString(R.string.app_name)
        val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(c)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun create(contexto: Context, id: Int, intent: Intent, titulo: String, texto: String) {
        // criar canal para mostrar notificação
        createChannel(App.getInstance())
        // Intent para disparar broadcast
        val p = PendingIntent.getActivity(contexto, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Cria notificação
        val builder = NotificationCompat.Builder(contexto, CHANNEL_ID)
            .setContentIntent(p)
            .setContentTitle(titulo)
            .setContentText(texto)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        // disparar notificacao
        with(NotificationManagerCompat.from(App.getInstance())) {
            val n = builder.build()
            notify(id, n)
        }
    }
}