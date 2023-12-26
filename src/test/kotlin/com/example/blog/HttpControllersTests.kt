package com.example.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

	@MockkBean
	lateinit var userRepository: UserRepository

	@MockkBean
	lateinit var articleRepository: ArticleRepository

	@Test
	fun `List articles`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		val lorem5Article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
		val ipsumArticle = Article("Ipsum", "Ipsum", "dolor sit amet", johnDoe)
		val anotherArticle = Article("Another Article", "Another Article Content", "Additional data", johnDoe)
		val thirdArticle = Article("Third Article", "Third Article Content", "Additional data", johnDoe)
		val fourthArticle = Article("Fourth Article", "Fourth Article Content", "Additional data", johnDoe)
		val fifthArticle = Article("Fifth Article", "Fifth Article Content", "Additional data", johnDoe)

		every { articleRepository.findAllByOrderByAddedAtDesc() } returns
				listOf(
					lorem5Article,
					ipsumArticle,
					anotherArticle,
					thirdArticle,
					fourthArticle,
					fifthArticle
				)

		mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("\$.[0].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
			.andExpect(jsonPath("\$.[1].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))
			.andExpect(jsonPath("\$.[2].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[2].slug").value(anotherArticle.slug))
			.andExpect(jsonPath("\$.[3].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[3].slug").value(thirdArticle.slug))
			.andExpect(jsonPath("\$.[4].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[4].slug").value(fourthArticle.slug))
			.andExpect(jsonPath("\$.[5].author.login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[5].slug").value(fifthArticle.slug))
	}

	@Test
	fun `List users`() {
		val johnDoe = User("johnDoe", "John", "Doe")
		val janeDoe = User("janeDoe", "Jane", "Doe")
		every { userRepository.findAll() } returns listOf(johnDoe, janeDoe)

		mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk)
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("\$.[0].login").value(johnDoe.login))
			.andExpect(jsonPath("\$.[1].login").value(janeDoe.login))
	}
}
