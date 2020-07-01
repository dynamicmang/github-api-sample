package com.search.domain.usecase

import com.search.domain.gateway.ContextGateway

class ContextUseCase(private val contextGateway: ContextGateway) {

    fun setQueryList(list: List<String>) = contextGateway.setQueryList(list)

    fun getQueryList(): List<String> = contextGateway.getQueryList()

}