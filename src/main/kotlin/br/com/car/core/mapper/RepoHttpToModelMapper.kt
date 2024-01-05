package br.com.car.core.mapper

import br.com.car.domain.http.RepoHttp
import br.com.car.domain.model.Repo

object RepoHttpToModelMapper {

    fun toModel(repos: List<RepoHttp>): List<Repo> {
        var count = 0
        return repos.map { repo ->
            Repo(
                id = count++,
                name = repo.name,
                description = repo.description,
                language = repo.language,
                topics = repo.topics,
                forks = repo.forks
            )
        }
    }
}