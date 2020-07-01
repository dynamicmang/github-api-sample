package com.search.domain.usecase

import io.reactivex.Scheduler

interface Schedulers {
    val subscribeOn: Scheduler
    val observeOn: Scheduler
}