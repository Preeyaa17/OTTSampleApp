package com.example.ownottapp.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ownottapp.model.Content
import com.example.ownottapp.ui.view.activities.VideoPlayerActivity
import com.example.ownottapp.util.StringConstants
import com.example.ownottsampleapp.databinding.ImageCardviewBinding


class ContentRowAdapter(var contentList: List<Content>) :
    RecyclerView.Adapter<ContentRowAdapter.ContentViewHolder>() {

//    class ContentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}
    inner class ContentViewHolder(val binding: ImageCardviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binding = ImageCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ContentViewHolder(binding)

//        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_cardview, parent,false)
//        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(StringConstants.imageBaseURL + contentList[position].posterPath)
            .into(holder.binding.cardPoster)
        if (contentList[position].title != null) {
            holder.binding.titleView.text = contentList[position].title
        } else if (contentList[position].name != null) {
            holder.binding.titleView.text = contentList[position].name
        } else {
            holder.binding.titleView.text = "TvShow"
        }
        if (contentList[position].originalLanguage.equals("en")) {
            holder.binding.languageView.text = "English"
        } else {
            holder.binding.languageView.text = contentList[position].originalLanguage
        }

        holder.binding.cardPoster.setOnClickListener(View.OnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, VideoPlayerActivity::class.java))
        })

    }


}