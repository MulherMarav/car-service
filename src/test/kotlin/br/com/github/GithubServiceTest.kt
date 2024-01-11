package br.com.github

import br.com.github.adapters.http.GithubHttpService
import br.com.github.core.service.GithubService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class GithubServiceTest: FunSpec({
    val repo = RepoFixture.getRepo()
    val repoHttp = RepoFixture.getRepoHttp()

    lateinit var githubHttpService: GithubHttpService
    lateinit var githubService: GithubService

    beforeTest {
        githubHttpService = mockk {
            coEvery { getReposByUsername(any()) } returns listOf(repoHttp)
        }

        githubService = GithubService(githubHttpService)
    }

    test("should return a list of repos by username") {
        githubService.getReposByUsername("username") shouldBe listOf(repo.copy(id = 0))

        coVerify(exactly = 1) { githubHttpService.getReposByUsername(any()) }
    }

    test("should throw a exception when not found repos by username") {
        coEvery { githubHttpService.getReposByUsername(any()) } returns emptyList()

        shouldThrow<RuntimeException> {
            githubService.getReposByUsername("username")
        }
    }
})