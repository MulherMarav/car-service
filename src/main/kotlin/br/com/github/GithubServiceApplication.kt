package br.com.github

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@EnableCaching
@SpringBootApplication
@ComponentScan("br.com.github")
class GithubServiceApplication

fun main(args: Array<String>) {
    runApplication<GithubServiceApplication>(*args)
}
