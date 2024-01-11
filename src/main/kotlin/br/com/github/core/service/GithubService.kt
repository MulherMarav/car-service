package br.com.github.core.service

import br.com.github.adapters.http.GithubHttpService
import br.com.github.core.mapper.RepoHttpToModelMapper
import br.com.github.domain.port.GithubService
import kotlinx.coroutines.coroutineScope
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
internal class GithubService(
    private val githubHttpService: GithubHttpService
): GithubService {

    @Cacheable(cacheNames = ["Repos"], key = "#root.method.name")
    override suspend fun getReposByUsername(username: String) = coroutineScope {
        println("get repos by retrofit")

        githubHttpService.getReposByUsername(username)
            .takeIf { it.isNotEmpty() }
            ?.let { RepoHttpToModelMapper.toModel(it) }
            ?: throw RuntimeException("No repositories found for username: $username")
    }

    /* scope coroutines (virtual thread), trata das requisições de forma paralela, recebe
     a requisição encaminha para service de destino, suspende, e quando tem um retorno, finaliza
     o processamento. */
    /* suspend - trabalha com o processamento, pode ser pausada e retomada depois.
     Elas podem executar uma operação de longa duração e esperarem que ela seja concluída sem bloquear.
     Só funciona no contexto coroutines.
     Não é necessário mais declarar .execute().body()? */
}