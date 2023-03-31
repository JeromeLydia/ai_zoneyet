package com.zoneyet.ai_zoneyet.model.api

import com.zoneyet.ai_zoneyet.model.api.bean.User
import retrofit2.http.*

interface ApiService {
    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): ApiResponse<User>

    @GET("users")
    suspend fun getUsers(): ApiResponse<List<User>>

    @POST("users")
    suspend fun createUser(@Body user: User): ApiResponse<User>

    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId: String, @Body user: User): ApiResponse<User>

    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String): ApiResponse<Unit>
}
