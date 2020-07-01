package com.search.domain.gateway

interface ContextGateway {
    fun getQueryList(): List<String>
    fun setQueryList(list: List<String>)
}