package br.com.github.domain.http

data class RepoHttp(
    val name: String?,
    val description: String?,
    val language: String?,
    val topics: List<String>?,
    val forks: Int?
)
