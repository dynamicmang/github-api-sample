package com.search.domain.usecase

import com.search.domain.entity.UserEntity
import com.search.domain.gateway.GithubGateway
import io.reactivex.Observable

class GetUserUseCase(schedulers: Schedulers, private val gateway: GithubGateway) : UseCase<List<UserEntity>>(schedulers) {

    override fun createObservable(param: Map<String, Any>?): Observable<List<UserEntity>> {
        return gateway.getUserList(param)
    }

}