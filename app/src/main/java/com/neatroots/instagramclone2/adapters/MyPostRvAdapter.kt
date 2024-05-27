package com.neatroots.instagramclone2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neatroots.instagramclone2.Models.Post
import com.neatroots.instagramclone2.databinding.MyPostRvDesignBinding
import com.squareup.picasso.Picasso

class MyPostRvAdapter(var context: Context, var postList:ArrayList<Post>):
    RecyclerView.Adapter<MyPostRvAdapter.ViewHolder>(){
    inner class ViewHolder(var binding :MyPostRvDesignBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPostRvAdapter.ViewHolder {
       var binding =MyPostRvDesignBinding.inflate(LayoutInflater.from(context),
           parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPostRvAdapter.ViewHolder, position: Int)
    {
        holder.binding.postImage
        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.postImage)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}




