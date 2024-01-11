package br.com.github.domain.model

data class Repo(
    val id: Int?,
    val name: String?,
    val description: String?,
    val language: String?,
    val topics: List<String>?,
    val forks: Int?
){
    fun isForked(): Boolean {
        return this.forks?.let { it > 0 } ?: false
    }
}
