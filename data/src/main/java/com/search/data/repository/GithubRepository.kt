package com.search.data.repository

import com.search.data.data.RepositoryData
import com.search.data.data.UserData
import com.search.data.data.UserDetailData
import com.search.data.remote.GithubService
import io.reactivex.Observable

class GithubRepository(private val api: GithubService) {

    fun getRepositoryList(param: Map<String, Any>?): Observable<List<RepositoryData>> {
        val query = param?.get("query") as String
        val page = param?.get("page") as Int
        return api.getRepositoryList(query = query, page = page).map { it.repositoryList }
    }

    fun getUserList(param: Map<String, Any>?): Observable<List<UserData>> {
        val query = param?.get("query") as String
        val page = param?.get("page") as Int
        return api.getUserList(query = query, page = page).map { it.userList }
    }

    fun getUserDetail(param: Map<String, Any>?): Observable<UserDetailData> {
        val id = param?.get("id") as Int
        return api.getUserDetail(id = id)
    }

}