package com.aulaandroid.ifoodclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MapActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }

    override fun onResume() {
        super.onResume()
        val mapaFragment = MapFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, mapaFragment)
            .commit()
    }
}
