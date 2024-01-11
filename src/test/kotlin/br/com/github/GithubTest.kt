package br.com.github

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class GithubTest: FunSpec({
        val repo = RepoFixture.getRepo()

        test("should return true when forks > than 0") {
            repo.isForked() shouldBe true
        }

        test("should return false when forks <= than 0") {
            repo.copy(forks = 0).isForked() shouldBe false
        }
}) // necessário instalar plugin do kotest no intellij