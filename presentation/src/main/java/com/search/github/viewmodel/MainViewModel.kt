package com.search.github.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.search.domain.usecase.ContextUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(private val contextUseCase: ContextUseCase) : BaseViewModel() {

    private val mutableKeywordData: MutableLiveData<List<String>> = MutableLiveData()
    val keywordData: LiveData<List<String>> = mutableKeywordData

    init {
        mutableKeywordData.value = contextUseCase.getQueryList()
    }

    fun clickSearch(query: String) {
        if (query.isEmpty()) {
            return
        }
        val list = mutableKeywordData.value?.toMutableList() ?: arrayListOf()
        if (!list.contains(query)) {
            if (list.size == 10) {
                list.removeAt(0)
            }
            list.add(query)
            contextUseCase.setQueryList(list)
        }
        mutableKeywordData.value = list
    }

    fun removeKeyword(keyword: String) {
        val list = mutableKeywordData.value!!.toMutableList()
        list.remove(keyword)
        mutableKeywordData.value = list
        contextUseCase.setQueryList(list)
    }

}