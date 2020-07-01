package com.search.domain.usecase

import com.search.domain.entity.UserDetailEntity
import com.search.domain.gateway.GithubGateway
import io.reactivex.Observable

class GetUserDetailUseCase(schedulers: Schedulers, private val gateway: GithubGateway) : UseCase<UserDetailEntity>(schedulers) {

    override fun createObservable(param: Map<String, Any>?): Observable<UserDetailEntity> {
        return gateway.getUserDetail(param)
    }

}