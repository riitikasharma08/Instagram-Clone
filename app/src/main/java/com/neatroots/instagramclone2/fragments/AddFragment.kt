package com.neatroots.instagramclone2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neatroots.instagramclone2.Post.PostActivity
import com.neatroots.instagramclone2.Post.ReelsActivity
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.databinding.FragmentAddBinding

class AddFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAddBinding.inflate(inflater, container, false)
        binding.post.setOnClickListener{
           activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
             activity?.finish()
        }
       binding.Reel.setOnClickListener{
           activity?.startActivity(Intent(requireContext(), ReelsActivity::class.java))
       }
           return binding.root
    }

    companion object {

            }
    }
