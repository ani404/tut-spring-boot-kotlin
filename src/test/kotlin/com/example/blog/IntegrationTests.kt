package com.example.blog

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

	@BeforeAll
	fun setup() {
		println(">> Setup")
	}

	@Test
	fun `Assert blog page title, content and status code`() {
		println(">> Assert blog page title, content and status code")
		val entity = restTemplate.getForEntity<String>("/")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains("<h1>Blog</h1>", "Lorem")
	}

	@Test
	fun `Assert article page title, content and status code`() {
		println(">> Assert article page title, content and status code")
		val title = "Lorem"
		val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(title, "Lorem", "dolor sit amet")
	}

	// Additional tests with different data
	@Test
	fun `Assert another article page title, content and status code`() {
		println(">> Assert another article page title, content and status code")
		val title = "Another Article"
		val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(title, "Another Article Content", "Additional data")
	}

	// Additional tests with different data
	@Test
	fun `Assert third article page title, content and status code`() {
		println(">> Assert third article page title, content and status code")
		val title = "Third Article"
		val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(title, "Third Article Content", "Additional data")
	}

	// Adding more similar tests
	@Test
	fun `Assert fourth article page title, content and status code`() {
		println(">> Assert fourth article page title, content and status code")
		val title = "Fourth Article"
		val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(title, "Fourth Article Content", "Additional data")
	}

	@Test
	fun `Assert fifth article page title, content and status code`() {
		println(">> Assert fifth article page title, content and status code")
		val title = "Fifth Article"
		val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains(title, "Fifth Article Content", "Additional data")
	}

	@AfterAll
	fun teardown() {
		println(">> Tear down")
	}
}
