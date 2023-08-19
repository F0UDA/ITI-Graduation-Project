package com.mohsen.iti.core.repo

import com.mohsen.iti.core.data_source.remote.ApiInterface
import com.mohsen.iti.models.PostModel
import retrofit2.Response

class PostRepository(private val apiInterface: ApiInterface) {
    suspend fun getPostsByUserId(userId: Int): Response<List<PostModel>> {
        return apiInterface.getPostsByUserId(userId)
    }
}