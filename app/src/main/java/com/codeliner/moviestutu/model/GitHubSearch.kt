package com.codeliner.moviestutu.model

data class GitHubSearch(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)