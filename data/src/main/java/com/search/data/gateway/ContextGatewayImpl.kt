package com.search.data.gateway

import android.content.Context
import android.content.SharedPreferences
import com.search.domain.gateway.ContextGateway
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


class ContextGatewayImpl(private val context: Context) : ContextGateway {

    companion object {
        private const val QUERY_LIST = "QUERY_LIST"
        private const val PREFERENCE = "PREFERENCE"
    }

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
    }

    private val moshiAdapter: JsonAdapter<List<String>> by lazy {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
        moshi.adapter<List<String>>(type)
    }

    override fun getQueryList(): List<String> {
        val json = preferences.getString(QUERY_LIST, null)
        if (json.isNullOrEmpty()) {
            return emptyList()
        }
        return moshiAdapter.fromJson(json)!!
    }

    override fun setQueryList(list: List<String>) {
        val json = moshiAdapter.toJson(list)
        preferences.edit().putString(QUERY_LIST, json).apply()
    }

}