package com.search.github.di

import android.content.Context
import com.search.domain.usecase.Schedulers
import com.search.github.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: BaseApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideSchedulers(): Schedulers = RxSchedulers()

}