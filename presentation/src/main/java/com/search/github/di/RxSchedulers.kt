package com.search.github.di

import com.search.domain.usecase.Schedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class RxSchedulers : Schedulers {
    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()
    override val observeOn: Scheduler
        get() = AndroidSchedulers.mainThread()
}