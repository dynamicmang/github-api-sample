package com.search.domain.usecase

import io.reactivex.Observable

abstract class UseCase<Result>(private val schedulers: Schedulers) {

    abstract fun createObservable(param: Map<String, Any>? = null): Observable<Result>

    fun observable(param: Map<String, Any>? = null): Observable<Result> {
        return createObservable(param)
            .subscribeOn(schedulers.subscribeOn)
            .observeOn(schedulers.observeOn)
    }

}