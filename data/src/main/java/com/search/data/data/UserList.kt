package com.search.data.data

import com.squareup.moshi.Json

data class UserList(
    @field:Json(name = "total_count") val totalCount: Int,
    @field:Json(name = "items") val userList: List<UserData>
)