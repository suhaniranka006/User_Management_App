package com.example.user_management_app

import com.example.user_management_app.databinding.ActivityMainBinding


import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermanagementapp.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        binding.btnFetch.setOnClickListener {
            viewModel.fetchUsers()
        }

        binding.btnAdd.setOnClickListener {
            val dummy = User(name = "John Doe", email = "john@example.com", phone = "1234567890")
            viewModel.addUser(dummy)
        }

        // Observe LiveData
        viewModel.users.observe(this) { list ->
            binding.rvUsers.adapter = UserAdapter(list)
        }

        viewModel.message.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
