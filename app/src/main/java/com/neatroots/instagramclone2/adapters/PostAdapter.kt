package com.neatroots.instagramclone2.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.neatroots.instagramclone2.Models.Post
import com.neatroots.instagramclone2.Models.User
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.databinding.PostRvBinding
import com.neatroots.instagramclone2.utils.USER_NODE


class PostAdapter(var context: Context, private val postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.MyHolder>() {

    inner class MyHolder(var binding: PostRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = postList[position]

        // Fetch user details
        Firebase.firestore.collection(USER_NODE).document(post.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<User>()
                user?.let {
                    Glide.with(context).load(user.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                    holder.binding.name.text = user.name
                }
            }
            .addOnFailureListener { e ->
                // Handle the error appropriately
                e.printStackTrace()
            }

        // Bind post details
        Glide.with(context).load(post.postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
        try{
            val text = TimeAgo.using(post.time.toLong())
            holder.binding.time.text = text
        }catch (e:Exception){
            holder.binding.time.text = ""
        }
          holder.binding.share.setOnClickListener{
              var i = Intent(Intent.ACTION_SEND)
              i.type="text/plain"
              i.putExtra(Intent.EXTRA_TEXT,postList.get(position).postUrl)
              context.startActivity(i)
          }
        holder.binding.cscaption.text = post.caption // Corrected from cscaption to caption
        holder.binding.like.setOnLongClickListener {
            holder.binding.like.setImageResource(R.drawable.img_9)
            true // Should return true to indicate that the event was handled
        }
    }
}

