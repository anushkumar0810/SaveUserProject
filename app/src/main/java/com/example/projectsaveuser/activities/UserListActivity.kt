package com.example.projectsaveuser.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectsaveuser.adapters.UserAdapter
import com.example.projectsaveuser.databinding.ActivityUserListBinding
import com.example.projectsaveuser.helper.SharedPreferenceManager

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var editor: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editor = SharedPreferenceManager(this)

        initListeners()
        checkList(editor.getUserList())

    }

    private fun initListeners() {
        binding.back.setOnClickListener { finish() }
        binding.nullLay.setOnClickListener { finish() }

        binding.deleteUserData.setOnClickListener {
            if (editor.getUserList().isNotEmpty()){
                editor.clearUserData()
                Toast.makeText(this, "Users deleted successfully", Toast.LENGTH_SHORT).show()
                val emptyList = editor.getUserList()
                val adapter = UserAdapter(emptyList)
                binding.userRecView.adapter = adapter
                checkList(emptyList)
            } else {
                Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show()
            }
        }

        val userList = editor.getUserList()
        val adapter = UserAdapter(userList)
        binding.userRecView.layoutManager = LinearLayoutManager(this)
        binding.userRecView.adapter = adapter
    }

    private fun checkList(userList: List<*>) {
        if (userList.isEmpty()) {
            binding.nullLay.visibility = View.VISIBLE
            binding.userRecView.visibility = View.GONE
        } else {
            binding.nullLay.visibility = View.GONE
            binding.userRecView.visibility = View.VISIBLE
        }
    }
}