package com.fromu.paging3androomtoyproject.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader("Authorization", apiKey)
            .build()

        val response = chain.proceed(request)

        return response
    }
}