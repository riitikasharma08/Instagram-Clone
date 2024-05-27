package com.neatroots.instagramclone2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.neatroots.instagramclone2.Models.Post
import com.neatroots.instagramclone2.Models.Reel
import com.neatroots.instagramclone2.adapters.MyPostRvAdapter
import com.neatroots.instagramclone2.adapters.MyReelAdapter
import com.neatroots.instagramclone2.databinding.FragmentAddBinding
import com.neatroots.instagramclone2.databinding.FragmentMyReelsBinding
import com.neatroots.instagramclone2.utils.REEL

class MyReelsFragment : Fragment() {
private  lateinit var binding: FragmentMyReelsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMyReelsBinding.inflate(inflater, container, false)
        var reelList =ArrayList<Reel>()
        var adapter = MyReelAdapter(requireContext(),reelList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).get().addOnSuccessListener {
            var tempList = arrayListOf<Reel>()
            for (i in it.documents){
                var reel: Reel =i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}



