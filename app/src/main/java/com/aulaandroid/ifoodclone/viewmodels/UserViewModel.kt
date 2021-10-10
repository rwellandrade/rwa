package com.aulaandroid.ifoodclone.viewmodels

import androidx.lifecycle.ViewModel

class User(name : String) {

}

class UserViewModel : ViewModel() {
    private val correctLogin = "aluno";
    private val correctPassword = "impacta";
    var user : User? = null
    fun login(login: String, pass: String) : Boolean {
        if (login == correctLogin && pass == correctPassword)
            user = User(login)
        return user != null
    }

}