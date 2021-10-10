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
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aulaandroid.ifoodclone.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        setupToolBarAndNavView()
        toast = Toast.makeText(baseContext, "", Toast.LENGTH_SHORT);
    }

    private fun setupToolBarAndNavView() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navViewMain
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main, R.id.nav_login, R.id.nav_restaurants
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        (menu?.findItem(R.id.action_search)?.actionView as SearchView).setOnQueryTextListener(object :
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//                // digitando
//                toast.cancel()
//                toast = Toast.makeText(
//                    applicationContext,
//                    "Digitando texto: $newText",
//                    Toast.LENGTH_SHORT
//                )
//                toast.show()
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                toast.cancel()
//                toast = Toast.makeText(
//                    applicationContext,
//                    "Busca realizada: $query",
//                    Toast.LENGTH_SHORT
//                )
//                toast.show()
//                return true
//            } // pressionou para buscar
//        })
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
//            navigator.goToSecond(this, nextTitle);
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

}