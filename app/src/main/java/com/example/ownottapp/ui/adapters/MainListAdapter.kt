package com.example.ownottapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ownottapp.model.ContentRow
import com.example.ownottapp.ui.ItemOffsetDecoration
import com.example.ownottsampleapp.R
import com.example.ownottsampleapp.databinding.ContentRowBinding

class MainListAdapter(var mainContentList:ArrayList<ContentRow>): RecyclerView.Adapter<MainListAdapter.CustomViewHolder>(){

    inner class CustomViewHolder(val binding: ContentRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ContentRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomViewHolder(binding)
//        val view=LayoutInflater.from(parent.context).inflate(R.layout.content_row,parent,false)
//        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mainContentList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val adapter= ContentRowAdapter(mainContentList[position].list)

//        holder.itemView.visibility = View.GONE
//        holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)

        holder.binding.contentRowRecyclerViewId.layoutManager= LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL,false)
        holder.binding.headerTitleView.text=mainContentList[position].title
        holder.binding.contentRowRecyclerViewId.adapter=adapter
        holder.binding.contentRowRecyclerViewId.addItemDecoration(ItemOffsetDecoration(8))

    }

//    class CustomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){}
}