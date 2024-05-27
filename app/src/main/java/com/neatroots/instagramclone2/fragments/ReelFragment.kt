package com.neatroots.instagramclone2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.neatroots.instagramclone2.Models.Reel
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.adapters.ReelAdapter
import com.neatroots.instagramclone2.databinding.FragmentReelBinding
import com.neatroots.instagramclone2.utils.REEL

class ReelFragment : Fragment() {
    private lateinit var binding:FragmentReelBinding
    lateinit var adapter: ReelAdapter
    var reelList =ArrayList<Reel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding = FragmentReelBinding.inflate(inflater, container, false)
        adapter = ReelAdapter(requireContext(),reelList)
        binding.viewPager.adapter=adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList=ArrayList<Reel>()
            reelList.clear()
            for (i in it.documents){
                var reel = i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
            reelList.reverse()
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}


