package com.aulaandroid.ifoodclone.data

import com.google.gson.GsonBuilder

open class BaseData {
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}