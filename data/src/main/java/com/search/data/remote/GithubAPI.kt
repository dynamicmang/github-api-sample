package com.search.data.remote

import com.search.data.BuildConfig
import com.search.data.data.RepositoryList
import com.search.data.data.UserDetailData
import com.search.data.data.UserList
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class GithubAPI(baseUrl: String = BuildConfig.API_URL) : GithubService {

    companion object {
        private const val TIMEOUT = 10L
    }

    private val service: GithubService

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthenticatorInterceptor())

        val client = httpClient.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(GithubService::class.java)

    }

    override fun getRepositoryList(query: String, sort: String, page: Int, perPage: Int): Observable<RepositoryList> {
        return service.getRepositoryList(query = query, page = page)
    }

    override fun getUserList(query: String, page: Int, perPage: Int): Observable<UserList> {
        return service.getUserList(query = query, page = page)
    }

    override fun getUserDetail(id: Int): Observable<UserDetailData> {
        return service.getUserDetail(id = id)
    }

}