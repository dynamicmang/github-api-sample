package com.search.github.di

import com.search.domain.gateway.ContextGateway
import com.search.domain.gateway.GithubGateway
import com.search.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providesContextUseCase(contextGateway: ContextGateway): ContextUseCase = ContextUseCase(contextGateway)

    @Provides
    @Singleton
    fun providesGetRepositoryUseCase(schedulers: Schedulers, gateway: GithubGateway): GetRepositoryUseCase = GetRepositoryUseCase(schedulers, gateway)

    @Provides
    @Singleton
    fun providesGetUserUseCase(schedulers: Schedulers, gateway: GithubGateway): GetUserUseCase = GetUserUseCase(schedulers, gateway)

    @Provides
    @Singleton
    fun providesGetUserDetailUseCase(schedulers: Schedulers, gateway: GithubGateway): GetUserDetailUseCase = GetUserDetailUseCase(schedulers, gateway)

}