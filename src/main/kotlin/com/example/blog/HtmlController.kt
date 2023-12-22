package com.example.blog

import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

// Controller handling HTML views for the blog
@Controller
class HtmlController(
	private val repository: ArticleRepository,
	private val properties: BlogProperties
) {

	// Retrieve all articles and render the blog page
	@GetMapping("/")
	fun blog(model: Model): String {
		// Setting attributes for the model
		model["title"] = properties.title
		model["banner"] = properties.banner
		model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
		return "blog" // Return the blog template
	}

	// Retrieve a specific article by slug and render its details
	@GetMapping("/article/{slug}")
	fun article(@PathVariable slug: String, model: Model): String {
		val article = repository
			.findBySlug(slug)
			?.render()
			?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")

		// Setting attributes for the model
		model["title"] = article.title
		model["article"] = article
		return "article" // Return the article template
	}

	// Extension function to render an article
	fun Article.render() = RenderedArticle(
		slug,
		title,
		headline,
		content,
		author,
		addedAt.format()
	)

	// Data class representing a rendered article
	data class RenderedArticle(
		val slug: String,
		val title: String,
		val headline: String,
		val content: String,
		val author: User,
		val addedAt: String
	)
}
