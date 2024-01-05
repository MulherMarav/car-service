package br.com.car.adapters.http

import br.com.car.domain.http.RepoHttp
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

@Service
interface GithubHttpService {

    @GET("/users/{username}/repos")
    fun getReposByUsername(@Path("username") username: String): Call<List<RepoHttp>>
}
