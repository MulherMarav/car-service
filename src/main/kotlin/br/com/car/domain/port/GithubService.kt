package br.com.car.domain.port

import br.com.car.domain.model.Repo

interface GithubService {

    fun getReposByUsername(username: String): List<Repo>?
}