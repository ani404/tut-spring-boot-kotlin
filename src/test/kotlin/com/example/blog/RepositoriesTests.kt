package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
	val entityManager: TestEntityManager,
	val userRepository: UserRepository,
	val articleRepository: ArticleRepository
) {

	@Test
	fun `When findByIdOrNull then return First Article`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		entityManager.persist(johnDoe)
		val firstArticle = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
		entityManager.persist(firstArticle)
		entityManager.flush()

		val found = articleRepository.findByIdOrNull(firstArticle.id!!)
		assertThat(found).isEqualTo(firstArticle)
	}

	@Test
	fun `When findByIdOrNull then return Second Article`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		entityManager.persist(johnDoe)
		val secondArticle = Article("Ipsum", "Ipsum", "dolor sit amet", johnDoe)
		entityManager.persist(secondArticle)
		entityManager.flush()

		val found = articleRepository.findByIdOrNull(secondArticle.id!!)
		assertThat(found).isEqualTo(secondArticle)
	}

	@Test
	fun `When findByIdOrNull then return Another Article`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		entityManager.persist(johnDoe)
		val anotherArticle = Article("Another Article", "Another Article Content", "Additional data", johnDoe)
		entityManager.persist(anotherArticle)
		entityManager.flush()

		val found = articleRepository.findByIdOrNull(anotherArticle.id!!)
		assertThat(found).isEqualTo(anotherArticle)
	}

	@Test
	fun `When findByIdOrNull then return Third Article`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		entityManager.persist(johnDoe)
		val thirdArticle = Article("Third Article", "Third Article Content", "Additional data", johnDoe)
		entityManager.persist(thirdArticle)
		entityManager.flush()

		val found = articleRepository.findByIdOrNull(thirdArticle.id!!)
		assertThat(found).isEqualTo(thirdArticle)
	}

	// More similar tests for other articles and users

	@Test
	fun `When findByLogin then return JohnDoe User`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		entityManager.persist(johnDoe)
		entityManager.flush()

		val user = userRepository.findByLogin(johnDoe.login)
		assertThat(user).isEqualTo(johnDoe)
	}

	// More similar tests for other users
}
