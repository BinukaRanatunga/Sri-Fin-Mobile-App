package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madproject.databinding.ActivityDeleteBinding
import com.example.madproject.databinding.ActivityReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Read : AppCompatActivity() {

    private lateinit var binding: ActivityReadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readbtn.setOnClickListener {
            val userName: String = binding.unr.text.toString()
            if (userName.isNotEmpty())

                readData(userName)
        else
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_LONG).show()

        }
    }
    private fun readData(userName: String) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userName).get().addOnSuccessListener {
            if(it.exists()){
                val username =it.child("empName").value
                val email =it.child("empEmail").value
                val phone =it.child("empPhoneNo").value
                val job =it.child("empJob").value
                Toast.makeText(this, "Successfuly Read", Toast.LENGTH_LONG).show()
                binding.unr.text.clear()
                binding.one.text =username.toString()
                binding.two.text =email.toString()
                binding.three.text =job.toString()
                binding.four.text =phone.toString()
            }else{
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_LONG).show()

            }
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }
    }
}
