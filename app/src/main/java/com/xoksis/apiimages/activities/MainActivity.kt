package com.xoksis.apiimages.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xoksis.apiimages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Binding.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cardViewDownloadedImages.setOnClickListener {
            startActivity(Intent(this@MainActivity, DownloadedImagesActivity::class.java))
        }

        binding.cardViewShowImages.setOnClickListener {
            startActivity(Intent(this@MainActivity, ShowImageActivity::class.java))
        }

    }
}