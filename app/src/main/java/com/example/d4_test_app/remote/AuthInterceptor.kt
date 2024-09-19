package com.example.d4_test_app.remote

import com.example.d4_test_app.remote.BASE_URL.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .addHeader("accept", "application/json")
            .build()
       return chain.proceed(request)
    }
}