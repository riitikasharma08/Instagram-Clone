package com.neatroots.instagramclone2.Post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.neatroots.instagramclone2.HomeActivity
import com.neatroots.instagramclone2.Models.Post
import com.neatroots.instagramclone2.Models.User
import com.neatroots.instagramclone2.R
import com.neatroots.instagramclone2.databinding.ActivityPostBinding
import com.neatroots.instagramclone2.utils.POST
import com.neatroots.instagramclone2.utils.POST_FOLDER
import com.neatroots.instagramclone2.utils.USER_NODE
import com.neatroots.instagramclone2.utils.USER_PROFILE_FOLDER
import com.neatroots.instagramclone2.utils.uploadImage

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectImage.setImageURI(uri)
                    imageUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            finish()
        }
        binding.selectImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
            finish()
        }
        binding.postButton.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document().get()
                .addOnSuccessListener {
                    var user = it.toObject<User>()!!
                    val post: Post = Post(
                      postUrl =   imageUrl!!,
                        caption = binding.caption.editText?.text.toString(),
                      uid =Firebase.auth.currentUser!!.uid ,
                        time = System.currentTimeMillis().toString()
                    )
                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post)
                            .addOnSuccessListener {
                                startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                                finish()
                            }
                    }
                }
        }
    }

    private fun Post(postUrl: String, caption: String, uid: String, time: String): Post {
        TODO("Not yet implemented")
    }
}








