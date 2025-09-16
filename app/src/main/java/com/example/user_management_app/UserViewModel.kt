package com.example.user_management_app
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel(private val repo: UserRepository) : ViewModel() {

    val users = MutableLiveData<List<User>>()
    val message = MutableLiveData<String>()

    fun fetchUsers() {
        viewModelScope.launch {
            val response = repo.getUsers()
            if (response.isSuccessful) {
                users.value = response.body()
            } else {
                message.value = "Error fetching users"
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            val response = repo.createUser(user)
            if (response.isSuccessful) {
                message.value = "User added successfully!"
                fetchUsers()
            } else {
                message.value = "Error adding user"
            }
        }
    }

    fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            val response = repo.updateUser(id, user)
            if (response.isSuccessful) {
                message.value = "User updated!"
                fetchUsers()
            } else {
                message.value = "Error updating user"
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            val response = repo.deleteUser(id)
            if (response.isSuccessful) {
                message.value = "User deleted!"
                fetchUsers()
            } else {
                message.value = "Error deleting user"
            }
        }
    }
}
