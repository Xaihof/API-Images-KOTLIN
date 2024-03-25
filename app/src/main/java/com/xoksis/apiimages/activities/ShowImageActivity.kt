package com.xoksis.apiimages.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.xoksis.apiimages.adapter_classes.ImageAdapter
import com.xoksis.apiimages.data_classes.ImageData
import com.xoksis.apiimages.databinding.ActivityShowImageBinding

class ShowImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowImageBinding

    var imageList = arrayListOf<ImageData>()

    val link = "https://apps.softobook.com/appsApis/gardenIdeas/flowerGardenImages.php" // API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromAPI(link)

    }

    fun getDataFromAPI(link: String) {

        val reqQueue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET, link, null, { res ->

            val jsonArray = res.getJSONArray("appImages")

            for (i in 0 until jsonArray.length()) {
                val jsonObject1 = jsonArray.getJSONObject(i)

                val image =
                    ImageData(jsonObject1.getString("catId"), jsonObject1.getString("imageUrl"))

                imageList.add(image)
            }

            binding.recyclerViewShowImages.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewShowImages.adapter = ImageAdapter(imageList)

        }, { err ->

            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
        })
        reqQueue.add(request)

    }

}