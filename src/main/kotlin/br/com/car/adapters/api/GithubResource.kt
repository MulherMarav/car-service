package br.com.car.adapters.api

import br.com.car.domain.port.GithubService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
class GithubResource(
    private val githubService: GithubService
) {
    @GetMapping("/{username}")
    fun getReposByUsername(@PathVariable username: String) =
        githubService.getReposByUsername(username)
}