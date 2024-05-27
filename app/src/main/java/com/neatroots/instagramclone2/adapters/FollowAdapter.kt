package com.neatroots.instagramclone2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neatroots.instagramclone2.Models.User
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.databinding.FollowRvBinding

class FollowAdapter(var context: Context, var folllowList:ArrayList<User>): RecyclerView.Adapter<FollowAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: FollowRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowAdapter.ViewHolder {
       var binding=FollowRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(folllowList.get(position).image)
            .placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.name.text=folllowList.get(position).name

    }

    override fun getItemCount(): Int {
       return folllowList.size
    }
}






