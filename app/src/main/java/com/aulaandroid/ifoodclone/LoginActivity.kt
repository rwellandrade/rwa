package com.aulaandroid.ifoodclone

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.aulaandroid.ifoodclone.databinding.ActivityLoginBinding
import com.aulaandroid.ifoodclone.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var navigator: Navigator

    private val correctLogin = "aluno";
    private val correctPassword = "impacta";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginInput = findViewById<EditText>(R.id.tv_user);
        val loginPassword = findViewById<EditText>(R.id.tv_password);
        val loginBt = findViewById<Button>(R.id.bt_login);

        loginBt.setOnClickListener { view ->
            hideKeyboard(view)
            val login = loginInput.text.toString()
            val pass = loginPassword.text.toString()
            if (login == correctLogin && pass == correctPassword) {
                navigator.goToMain(this)
            } else {
                Toast.makeText(view.context, getText(R.string.invalid_password), Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}