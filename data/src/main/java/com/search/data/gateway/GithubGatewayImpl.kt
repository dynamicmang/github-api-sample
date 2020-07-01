package com.search.data.gateway

import com.search.data.repository.GithubRepository
import com.search.domain.entity.RepositoryEntity
import com.search.domain.entity.UserDetailEntity
import com.search.domain.entity.UserEntity
import com.search.domain.gateway.GithubGateway
import io.reactivex.Observable

class GithubGatewayImpl(private val repository: GithubRepository) : GithubGateway {

    override fun getRepositoryList(param: Map<String, Any>?): Observable<List<RepositoryEntity>> {
        return repository.getRepositoryList(param).map { list ->
            list.map {
                RepositoryEntity(it.repositoryName, it.language ?: "No Language", it.lastCommitDate, it.starCount)
            }
        }
    }

    override fun getUserList(param: Map<String, Any>?): Observable<List<UserEntity>> {
        return repository.getUserList(param).map { list ->
            list.map {
                UserEntity(it.id)
            }
        }
    }

    override fun getUserDetail(param: Map<String, Any>?): Observable<UserDetailEntity> {
        return repository.getUserDetail(param).map {
            UserDetailEntity(it.thumbnail, it.repositoryCount, it.location ?: "No Location", it.name ?: "No Name")
        }
    }

}