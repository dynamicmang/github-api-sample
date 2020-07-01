package com.search.data.remote

import com.search.data.data.RepositoryList
import com.search.data.data.UserDetailData
import com.search.data.data.UserList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    fun getRepositoryList(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Observable<RepositoryList>

    @GET("search/users")
    fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): Observable<UserList>

    @GET("user/{id}")
    fun getUserDetail(@Path("id") id: Int): Observable<UserDetailData>

}