package com.example.photogrid

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photogrid.photoModel.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_photo.view.*

class PhotoAdapter(private val photosList: ArrayList<Photo>, val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    override fun getItemCount(): Int = photosList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_row_photo, parent, false)
        return PhotoAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
        holder!!.bindingvalues(photosList.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingvalues(get: Photo) {
            Picasso.get().load(get.thumbnailUrl).into(itemView.ivImage)
            itemView.tvTitle.text = get.title
        }
    }
}