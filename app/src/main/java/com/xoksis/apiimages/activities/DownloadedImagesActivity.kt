package com.xoksis.apiimages.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.xoksis.apiimages.adapter_classes.DownloadeImageAdapter
import com.xoksis.apiimages.databinding.ActivityDownloadedImagesBinding
import com.xoksis.apiimages.sqlite_classes.ImageDatabaseHelper

class DownloadedImagesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDownloadedImagesBinding
    private lateinit var db: ImageDatabaseHelper
    private lateinit var downloadedImageAdapter: DownloadeImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = ImageDatabaseHelper(this)
        downloadedImageAdapter = DownloadeImageAdapter(db.getAllImages(), this)

        binding.recyclerViewDownloadedImages.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewDownloadedImages.adapter = downloadedImageAdapter

    }

    override fun onResume() {
        super.onResume()

        downloadedImageAdapter.refreshData(db.getAllImages())

    }
}