package com.example.user_management_app

class UserRepository {

    suspend fun getUsers() = RetrofitInstance.api.getUsers()
    suspend fun createUser(user: User) = RetrofitInstance.api.createUser(user)
    suspend fun updateUser(id: Int, user: User) = RetrofitInstance.api.updateUser(id, user)
    suspend fun deleteUser(id: Int) = RetrofitInstance.api.deleteUser(id)

}