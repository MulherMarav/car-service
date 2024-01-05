package br.com.car.adapters.config

import br.com.car.adapters.http.GithubHttpService
import io.github.resilience4j.retrofit.CircuitBreakerCallAdapter
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class GithubHttpConfig(
    private val circuitBreakerConfig: CircuitBreakerConfig
) {

    private companion object {
        const val BASE_URL = "http://api.github.com"
    }

    private fun buildClient() = OkHttpClient.Builder().build()

    private fun buildRetrofit() = Retrofit.Builder()
        .addCallAdapterFactory(CircuitBreakerCallAdapter.of(circuitBreakerConfig.getCircuitBreaker()))
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildClient())
        .build()

    @Bean
    fun githubHttpService(): GithubHttpService = buildRetrofit().create(GithubHttpService::class.java)
}