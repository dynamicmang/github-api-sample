package com.search.data.data

import com.squareup.moshi.Json

data class RepositoryData(
    @field:Json(name = "full_name") val repositoryName: String,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "updated_at") val lastCommitDate: String,
    @field:Json(name = "stargazers_count") val starCount: Int
)