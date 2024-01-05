package br.com.car.core.service

import br.com.car.adapters.http.GithubHttpService
import br.com.car.core.mapper.RepoHttpToModelMapper
import br.com.car.domain.port.GithubService
import org.springframework.stereotype.Service

@Service
internal class GithubService(
    private val githubHttpService: GithubHttpService
): GithubService {

    override fun getReposByUsername(username: String) =
        githubHttpService.getReposByUsername(username)
            .execute().body()?.let {
                RepoHttpToModelMapper.toModel(it)
            }
}