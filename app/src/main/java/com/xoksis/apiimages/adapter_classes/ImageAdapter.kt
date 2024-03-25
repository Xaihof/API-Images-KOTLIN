package com.xoksis.apiimages.adapter_classes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xoksis.apiimages.R
import com.xoksis.apiimages.activities.ImageDetailActivity
import com.xoksis.apiimages.data_classes.ImageData

class ImageAdapter(private val imageList: ArrayList<ImageData>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.imageViewImage)
        val catId = itemView.findViewById<TextView>(R.id.textViewCatId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout_show_images, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val image = imageList[position]

        Glide.with(context).load(image.pic_link).into(holder.image)
        holder.catId.text = image.cat_Id

        holder.itemView.setOnClickListener {

            val intent = Intent(context, ImageDetailActivity::class.java)
            intent.putExtra("image", image.pic_link)
            intent.putExtra("catId",image.cat_Id)
            context.startActivity(intent)

        }
    }
}