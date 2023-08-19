package com.mohsen.iti.core.repo

import com.mohsen.iti.core.data_source.remote.ApiInterface
import com.mohsen.iti.core.model.body.LoginBodyRequest
import com.mohsen.iti.core.model.response.UserResponse
import retrofit2.Response

class LoginRepository(private val apiInterface: ApiInterface) {
    suspend fun login(username: String, password: String): Response<UserResponse> {
        val bodyRequest = LoginBodyRequest(username, password)
        return apiInterface.login(bodyRequest)
    }
}