package br.com.github.adapters.api

import br.com.github.domain.port.GithubService
import kotlinx.coroutines.runBlocking
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
    fun getReposByUsername(@PathVariable username: String) = runBlocking {
        githubService.getReposByUsername(username)
    }
    // runBlocking - contexto coroutines (virtual thread), bloqueante (bloqueia a thread principal) - não recomendado em produção
}