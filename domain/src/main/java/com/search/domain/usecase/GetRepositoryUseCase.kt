package com.search.domain.usecase

import com.search.domain.entity.RepositoryEntity
import com.search.domain.gateway.GithubGateway
import io.reactivex.Observable

class GetRepositoryUseCase(schedulers: Schedulers, private val gateway: GithubGateway) : UseCase<List<RepositoryEntity>>(schedulers) {

    override fun createObservable(param: Map<String, Any>?): Observable<List<RepositoryEntity>> {
        return gateway.getRepositoryList(param)
    }

}