package com.xoksis.apiimages.adapter_classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xoksis.apiimages.R
import com.xoksis.apiimages.sqlite_classes.Image

class DownloadeImageAdapter(private var allImages: List<Image>, private var context: Context) :
    RecyclerView.Adapter<DownloadeImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imageViewImage)
        val catId = itemView.findViewById<TextView>(R.id.textViewCatId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_downloaded_images, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = allImages.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val image = allImages[position]

        Glide.with(context).load(image.imageLink).into(holder.image)
        holder.catId.text = image.catID
    }

    fun refreshData(newImages: List<Image>) {
        allImages = newImages
        notifyDataSetChanged()
    }
}