package com.example.madproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.madproject.databinding.ActivityFeedbackBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Feedback : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            val name = binding.fUname.text.toString()
            val email = binding.fEmail.text.toString()
            val feedback = binding.feedback.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Feedbacks")
            val User = feedbackModel(name, email, feedback)
            database.child(name).setValue(User).addOnSuccessListener {
                binding.fUname.text.clear()
                binding.fEmail.text.clear()
                binding.feedback.text.clear()

                Toast.makeText(this, "Successfully Submited", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()


            }

        }
    }
}




