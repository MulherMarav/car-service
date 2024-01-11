package br.com.github.domain.port

import br.com.github.domain.model.Repo

interface GithubService {

    suspend fun getReposByUsername(username: String): List<Repo>?

    /* suspend - trabalha com o processamento, pode ser pausada e retomada depois.
     Elas podem executar uma operação de longa duração e esperarem que ela seja concluída sem bloquear.
     Só funciona no contexto coroutines
     */
}