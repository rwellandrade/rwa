package com.aulaandroid.ifoodclone

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.ui.AppBarConfiguration
import com.aulaandroid.ifoodclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: Navigator
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        toast = Toast.makeText(baseContext, "", Toast.LENGTH_SHORT);
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val v = menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_search)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                // digitando
                toast.cancel()
                toast = Toast.makeText(applicationContext, "Digitando texto: $newText", Toast.LENGTH_SHORT)
                toast.show()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                toast.cancel()
                toast = Toast.makeText(applicationContext, "Busca realizada: $query", Toast.LENGTH_SHORT)
                toast.show()
                return true
            } // pressionou para buscar
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var nextTitle = ""
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(
                    applicationContext,
                    getText(R.string.action_update),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            R.id.action_exit -> {
                finish()
            }
            R.id.action_update -> {
                doUpdateAction()
            }
            R.id.action_add -> {
                nextTitle = getText(R.string.action_add).toString()
                Toast.makeText(applicationContext, getText(R.string.action_add), Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.action_settings -> {
                nextTitle = getText(R.string.action_settings).toString()
                Toast.makeText(applicationContext, getText(R.string.action_add), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        if (nextTitle.isNotEmpty()) {
            navigator.goToSecond(this, nextTitle);
        }
        return super.onOptionsItemSelected(item);
    }

    private fun doUpdateAction() {
        val progressBar = findViewById<ProgressBar>(R.id.pb_loading)
        val mainImage = findViewById<ImageView>(R.id.iv_main)
        progressBar.visibility = View.VISIBLE
        mainImage.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed(
            {
                progressBar.visibility = View.GONE
                mainImage.visibility = View.VISIBLE
            },
            5_000
        )
    }

    fun hideKeyboard(view: View) {
        // Enconder o teclado
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

}