package com.search.github.di

import android.content.Context
import com.search.data.gateway.ContextGatewayImpl
import com.search.data.gateway.GithubGatewayImpl
import com.search.data.remote.GithubAPI
import com.search.data.remote.GithubService
import com.search.data.repository.GithubRepository
import com.search.domain.gateway.ContextGateway
import com.search.domain.gateway.GithubGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesContextGateWay(context: Context): ContextGateway = ContextGatewayImpl(context)

    @Provides
    @Singleton
    fun providesGithubService(): GithubService = GithubAPI()

    @Provides
    @Singleton
    fun providesGithubRepository(githubService: GithubService): GithubRepository = GithubRepository(githubService)

    @Provides
    @Singleton
    fun providesGithubGateway(githubRepository: GithubRepository): GithubGateway = GithubGatewayImpl(githubRepository)

}