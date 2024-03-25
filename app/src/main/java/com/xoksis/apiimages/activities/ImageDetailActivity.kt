package com.xoksis.apiimages.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.xoksis.apiimages.R
import com.xoksis.apiimages.databinding.ActivityImageDetailBinding
import com.xoksis.apiimages.sqlite_classes.Image
import com.xoksis.apiimages.sqlite_classes.ImageDatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailBinding // Binding.
    private lateinit var imageLink: String
    private lateinit var catId: String
    private lateinit var db: ImageDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ImageDatabaseHelper(this)

        // Image from previous activity.
        imageLink = intent.getStringExtra("image").toString()
        catId = intent.getStringExtra("catId").toString()
        Glide.with(this).load(imageLink).into(binding.imageViewImage)


        // Back Button on app bar.
        binding.topAppBar.setNavigationOnClickListener {

            finish()
        }

        // Download button on app bar.
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {

                R.id.download -> {

                    saveImageToDatabase(Image(0, imageLink, catId))
                    Toast.makeText(this, "Image Downloaded", Toast.LENGTH_SHORT).show()

                }
            }

            true
        }

    }

    fun saveImageToDatabase(image: Image) {

        GlobalScope.launch(Dispatchers.IO) {
            db.insertImage(image)
        }

    }
}