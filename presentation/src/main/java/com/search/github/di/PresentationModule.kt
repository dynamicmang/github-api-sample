package com.search.github.di

import com.search.github.activity.MainActivity
import com.search.github.activity.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PresentationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun contributesSearchActivity(): SearchActivity

}