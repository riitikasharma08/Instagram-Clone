package com.neatroots.instagramclone2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.neatroots.instagramclone2.Models.User
import com.neatroots.instagramclone2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            if(binding.email.editText?.text.toString().equals("")or
            binding.pass.editText?.text.toString().equals("")){
                Toast.makeText(this@LoginActivity,"please fill all the details",Toast.LENGTH_SHORT).show()
            }else{
                var user = User(binding.email.editText?.text.toString(),
                binding.pass.editText?.text.toString())
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                           startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity,it.exception?.localizedMessage
                                ,Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}


