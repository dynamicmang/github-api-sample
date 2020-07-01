package com.search.domain.gateway

import com.search.domain.entity.RepositoryEntity
import com.search.domain.entity.UserDetailEntity
import com.search.domain.entity.UserEntity
import io.reactivex.Observable

interface GithubGateway {

    fun getRepositoryList(param: Map<String, Any>?): Observable<List<RepositoryEntity>>

    fun getUserList(param: Map<String, Any>?): Observable<List<UserEntity>>

    fun getUserDetail(param:Map<String, Any>?) : Observable<UserDetailEntity>

}