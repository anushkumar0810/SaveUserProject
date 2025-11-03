package com.example.projectsaveuser.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectsaveuser.databinding.ActivityMainBinding
import com.example.projectsaveuser.helper.SharedPreferenceManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var editor: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editor = SharedPreferenceManager(this)

        initListeners()
    }

    private fun initListeners() {

        binding.saveUserBtn.setOnClickListener {
            val firstName = binding.firstNameEdt.text.toString().trim()
            val lastName = binding.lastNameEdt.text.toString().trim()
            val age = binding.ageEdt.text.toString().trim()

            when {
                firstName.isEmpty() -> {
                    binding.firstNameEdt.error = "Please enter first name"
                    binding.firstNameEdt.requestFocus()
                }

                lastName.isEmpty() -> {
                    binding.lastNameEdt.error = "Please enter last name"
                    binding.lastNameEdt.requestFocus()
                }

                age.isEmpty() -> {
                    binding.ageEdt.error = "Please enter age"
                    binding.ageEdt.requestFocus()
                }

                else -> {
                    editor.saveUserData(firstName, lastName, age)
                    Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
                    binding.firstNameEdt.text?.clear()
                    binding.lastNameEdt.text?.clear()
                    binding.ageEdt.text?.clear()
                }
            }
        }

        binding.showUsersBtn.setOnClickListener {
            binding.firstNameEdt.clearFocus()
            binding.firstNameEdt.error = null
            binding.lastNameEdt.clearFocus()
            binding.lastNameEdt.error = null
            binding.ageEdt.clearFocus()
            binding.ageEdt.error = null
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }
}