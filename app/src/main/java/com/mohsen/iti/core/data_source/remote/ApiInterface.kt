package com.mohsen.iti.core.data_source.remote

import com.mohsen.iti.core.model.body.LoginBodyRequest
import com.mohsen.iti.core.model.response.UserResponse
import com.mohsen.iti.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostModel>>

    @GET("posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): Response<List<PostModel>>

    @GET("posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id: Int): Response<List<CommentModel>>

    @POST("auth/login")
    suspend fun login(@Body bodyRequest: LoginBodyRequest): Response<UserResponse>
}