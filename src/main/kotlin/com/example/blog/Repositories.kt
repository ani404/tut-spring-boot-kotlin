package com.example.blog

import org.springframework.data.repository.CrudRepository

// Repository interface for handling Article entities
interface ArticleRepository : CrudRepository<Article, Long> {
	// Find an article by its slug
	fun findBySlug(slug: String): Article?

	// Find all articles ordered by added date in descending order
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

// Repository interface for handling User entities
interface UserRepository : CrudRepository<User, Long> {
	// Find a user by their login
	fun findByLogin(login: String): User?
}
