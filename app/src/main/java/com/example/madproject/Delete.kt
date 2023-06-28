package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madproject.databinding.ActivityDeleteBinding
import com.example.madproject.databinding.ActivityEditProfileBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Delete : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inflate the layout using view binding
        binding.del.setOnClickListener{

            // Get the username from the text input field
            val userName = binding.un.text.toString()
            if(userName.isNotEmpty())
                deleteData(userName)
            else
                Toast.makeText(this, "Please enter the username", Toast.LENGTH_LONG).show()
        }
    }
    private fun deleteData(userName: String) {
        // Get a reference to the "Users" node in the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        // Remove the child node with the given user name from the "Users" node
        databaseReference.child(userName).removeValue().addOnSuccessListener {

            Toast.makeText(this, "Successfuly Deleted", Toast.LENGTH_LONG).show()

        }.addOnFailureListener { err ->
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
        }
    }
}
