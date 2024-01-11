package br.com.github.adapters.http

import br.com.github.domain.http.RepoHttp
import org.springframework.stereotype.Service
import retrofit2.http.GET
import retrofit2.http.Path

@Service
interface GithubHttpService {

    @GET("/users/{username}/repos")
    suspend fun getReposByUsername(@Path("username") username: String): List<RepoHttp>

    /* Quando coloca o suspend, o retrofit já entende que o retorno é um Call,
     não é mais necessário declarar Call<List<RepoHttp>>
     suspend - trabalha com o processamento, pode ser pausada e retomada depois.
     Elas podem executar uma operação de longa duração e esperarem que ela seja concluída sem bloquear.
     Só funciona no contexto coroutines
     */
}
