package com.search.data.data

import com.squareup.moshi.Json

data class UserData(
    @field:Json(name = "id") val id: Int
)