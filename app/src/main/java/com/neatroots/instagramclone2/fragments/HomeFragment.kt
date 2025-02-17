package com.neatroots.instagramclone2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.neatroots.instagramclone2.Models.Post
import com.neatroots.instagramclone2.Models.User
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.adapters.FollowAdapter
import com.neatroots.instagramclone2.adapters.PostAdapter
import com.neatroots.instagramclone2.databinding.FragmentHomeBinding
import com.neatroots.instagramclone2.utils.FOLLOW
import com.neatroots.instagramclone2.utils.POST

class HomeFragment(val templist: Collection<User>) : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var postList = ArrayList<Post>()
    private lateinit var adapter: PostAdapter
    private var folllowList=ArrayList<User>()
    private lateinit var followAdapter: FollowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = PostAdapter(requireContext(), postList)
        binding.postRv.layoutManager = LinearLayoutManager(requireContext())
        binding.postRv.adapter = adapter
          followAdapter= FollowAdapter(requireContext(),folllowList)
        binding.followRv.layoutManager=LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.followRv.adapter=followAdapter
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar2)

        // Fetch posts from Firestore
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW)
            .get().addOnSuccessListener {
                var  tempList = ArrayList<User>()
            for(i in it.documents){
                var user: User=i.toObject<User>()!!
                tempList.add(user)
            }
                folllowList.addAll(templist)
                followAdapter.notifyDataSetChanged()
        }
        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            val tempList = ArrayList<Post>() // Fixing the variable name issue
            postList.clear()
            for (i in it.documents) {
                val post: Post? = i.toObject<Post>()
                post?.let { tempList.add(it) }
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}


