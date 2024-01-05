package br.com.car.domain.model

data class Repo(
    val id: Int?,
    val name: String?,
    val description: String?,
    val language: String?,
    val topics: List<String>?,
    val forks: Int?
)
