package com.search.domain.entity

data class RepositoryEntity(
    val repositoryName: String,
    val language: String,
    val lastCommitDate: String,
    val starCount: Int
)