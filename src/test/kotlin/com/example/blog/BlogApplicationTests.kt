package com.example.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	lateinit var userRepository: UserRepository

	@Autowired
	lateinit var articleRepository: ArticleRepository

	@Test
	fun `Context loads`() {
		// Verifies that the context loads successfully
	}

	@Test
	fun `UserRepository should be accessible`() {
		// Verifies that UserRepository is accessible and not null
		assert(userRepository != null)
	}

	@Test
	fun `ArticleRepository should be accessible`() {
		// Verifies that ArticleRepository is accessible and not null
		assert(articleRepository != null)
	}

	// Add more test cases to cover specific functionalities or interactions
}
