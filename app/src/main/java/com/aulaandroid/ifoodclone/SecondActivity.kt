package com.aulaandroid.ifoodclone

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aulaandroid.ifoodclone.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (intent.hasExtra(navigator.nextTitle)) {
            val title = intent.getStringExtra(navigator.nextTitle)
            supportActionBar?.title = title
            val mainText = findViewById<TextView>(R.id.tv_welcome);
            mainText.text = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
