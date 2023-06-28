package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madproject.databinding.ActivityEditProfileBinding
import com.example.madproject.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditProfile : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button8.setOnClickListener{

            val userName = binding.editTextTextPersonName19.text.toString()
            val email = binding.editTextTextPersonName25.text.toString()
            val phone = binding.editTextTextPersonName28.text.toString()
            val job = binding.editTextTextPersonName20.text.toString()

            updateData(userName,email,phone,job)
        }
    }

    private fun updateData(userName: String, email: String, phone: String, job: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        //#map
        val user = mapOf<String,String>(
            "empEmail" to email,
            "empJob" to job,
            "empName" to userName,
            "empPhoneNo" to phone
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {
            binding.editTextTextPersonName19.text.clear()
            binding.editTextTextPersonName25.text.clear()
            binding.editTextTextPersonName28.text.clear()
            binding.editTextTextPersonName20.text.clear()
            Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{

            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}
