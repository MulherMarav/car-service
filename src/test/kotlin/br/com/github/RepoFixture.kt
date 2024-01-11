package br.com.github

import br.com.github.domain.http.RepoHttp
import br.com.github.domain.model.Repo

object RepoFixture {

    fun getRepo() = Repo(
        id = 1,
        name = "repo",
        description = "description",
        language = "language",
        topics = listOf("topic1", "topic2"),
        forks = 1
    )

    fun getRepoHttp() = RepoHttp(
        name = "repo",
        description = "description",
        language = "language",
        topics = listOf("topic1", "topic2"),
        forks = 1
    )
}