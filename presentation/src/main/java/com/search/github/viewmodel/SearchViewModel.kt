package com.search.github.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.search.domain.entity.RepositoryEntity
import com.search.domain.entity.UserDetailEntity
import com.search.domain.entity.UserEntity
import com.search.domain.usecase.GetRepositoryUseCase
import com.search.domain.usecase.GetUserDetailUseCase
import com.search.domain.usecase.GetUserUseCase
import io.reactivex.Observable
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repositoryUseCase: GetRepositoryUseCase,
    private val userUseCase: GetUserUseCase,
    private val userDetailUseCase: GetUserDetailUseCase
) : BaseViewModel() {

    var query: String = ""
        set(value) {
            field = value
            getRepository()
            getUser()
        }

    private val repositoryMutableData: MutableLiveData<List<RepositoryEntity>> = MutableLiveData()
    val repositoryData: LiveData<List<RepositoryEntity>> = repositoryMutableData
    val isRepositoryLoading = ObservableBoolean()

    private val userMutableData: MutableLiveData<List<UserDetailEntity>> = MutableLiveData()
    val userData: LiveData<List<UserDetailEntity>> = userMutableData
    val isUserLoading = ObservableBoolean()

    fun getRepository(page: Int = 1) {
        isRepositoryLoading.set(true)
        if (page == 1) {
            repositoryMutableData.value = emptyList()
        }
        val map = HashMap<String, Any>()
        map["query"] = "$query+sort:updated"
        map["page"] = page
        compositeDisposable.add(
            repositoryUseCase.observable(map)
                .subscribe({
                    repositoryMutableData.value = it
                    isRepositoryLoading.set(false)
                }, {
                    Log.e("BOMAP", "get repository error", it)
                    isRepositoryLoading.set(false)
                })
        )
    }

    fun getUser(page: Int = 1) {
        isUserLoading.set(true)
        if (page == 1) {
            userMutableData.value = emptyList()
        }
        val map = HashMap<String, Any>()
        map["query"] = query
        map["page"] = page
        compositeDisposable.add(
            userUseCase.observable(map)
                .subscribe({
                    getUserDetail(it)
                }, {
                    Log.e("BOMAP", "get user error", it)
                    isUserLoading.set(false)
                })
        )
    }

    private fun getUserDetail(userList: List<UserEntity>) {
        compositeDisposable.add(
            Observable.fromIterable(userList)
                .flatMap {
                    val map = HashMap<String, Int>()
                    map["id"] = it.id
                    userDetailUseCase.observable(map)
                }
                .toList()
                .subscribe({
                    userMutableData.value = it
                    isUserLoading.set(false)
                }, {
                    Log.e("BOMAP", "get user detail error", it)
                    isUserLoading.set(false)
                })
        )
    }

}