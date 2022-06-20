package com.codeliner.vitalchernavsky.model

data class GitHubSearch(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)