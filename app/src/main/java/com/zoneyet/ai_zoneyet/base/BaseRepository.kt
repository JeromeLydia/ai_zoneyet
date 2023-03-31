package com.zoneyet.ai_zoneyet.base

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <T> apiCall(call: suspend () -> Response<T>): Response<T> {
        return try {
            call.invoke()
        } catch (throwable: Throwable) {
            Response.error(500, ResponseBody.create("application/json".toMediaTypeOrNull(), ""))
        }
    }
}
