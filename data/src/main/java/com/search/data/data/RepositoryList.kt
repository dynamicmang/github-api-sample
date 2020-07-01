package com.search.data.data

import com.squareup.moshi.Json

data class RepositoryList(
    @field:Json(name = "total_count") val totalCount: Int,
    @field:Json(name = "items") val repositoryList: List<RepositoryData>
)