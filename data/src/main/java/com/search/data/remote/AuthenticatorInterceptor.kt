package com.search.data.remote

import com.search.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthenticatorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val currentRequest = chain.request()
            val newRequest = currentRequest.newBuilder()
                .header("Accept", "application/vnd.github.v3+json")
                .header("Authorization", "token " + BuildConfig.API_KEY)
                .build()
            return chain.proceed(newRequest)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return chain.proceed(chain.request())
    }

}