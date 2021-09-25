package com.aulaandroid.ifoodclone

import android.content.Context
import android.content.Intent

class Navigator {
        val nextTitle = "NEXT_TITLE"

    fun goToMain(ctx : Context) {
        val intent = Intent(ctx, MainActivity::class.java)
        ctx.startActivity(intent)
    }
    fun goToLogin(ctx : Context) {
        val intent = Intent(ctx, LoginActivity::class.java)
        ctx.startActivity(intent)
    }

    fun goToSecond(ctx: Context, nextTitle: String) {
        val intent = Intent(ctx, SecondActivity::class.java)
        intent.putExtra(this.nextTitle, nextTitle)
        ctx.startActivity(intent)
    }
}