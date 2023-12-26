package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

	@Bean
	fun databaseInitializer(userRepository: UserRepository, articleRepository: ArticleRepository) = ApplicationRunner {

		val johnDoe = userRepository.save(User("johnDoe", "John", "Doe"))

		// Initial data
		articleRepository.save(Article(
			title = "Lorem",
			headline = "Lorem",
			content = "dolor sit amet",
			author = johnDoe
		))
		articleRepository.save(Article(
			title = "Ipsum",
			headline = "Ipsum",
			content = "dolor sit amet",
			author = johnDoe
		))

		// Additional data matching test cases
		articleRepository.save(Article(
			title = "Another Article",
			headline = "Another Article Content",
			content = "Additional data",
			author = johnDoe
		))
		articleRepository.save(Article(
			title = "Third Article",
			headline = "Third Article Content",
			content = "Additional data",
			author = johnDoe
		))
		articleRepository.save(Article(
			title = "Fourth Article",
			headline = "Fourth Article Content",
			content = "Additional data",
			author = johnDoe
		))
		articleRepository.save(Article(
			title = "Fifth Article",
			headline = "Fifth Article Content",
			content = "Additional data",
			author = johnDoe
		))
	}
}
