package br.com.github.adapters.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CircuitBreakerConfig {

    //open circuit
    fun getConfig() = CircuitBreakerConfig.custom()
        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // quantidade de requisições
        .slidingWindowSize(10) // quantidade de requisições
        .slowCallRateThreshold(70.0f) // porcentagem de requisições lentas
        .slowCallDurationThreshold(Duration.ofMillis(2)) // duração de requisições lentas
        .failureRateThreshold(70.0f) // porcentagem de requisições com falhas
        .waitDurationInOpenState(Duration.ofSeconds(5)) // duração para circuito ficar aberto
        .permittedNumberOfCallsInHalfOpenState(3) // quantidade de requisições no half open
        .build()

    fun getCircuitBreaker() = CircuitBreakerRegistry.of(getConfig())
        .circuitBreaker("circuit-breaker-car-service")
}