package com.search.data.data

import com.squareup.moshi.Json

data class UserDetailData(
    @field:Json(name = "avatar_url") val thumbnail: String,
    @field:Json(name = "public_repos") val repositoryCount: Int = 0,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "name") val name: String?
)