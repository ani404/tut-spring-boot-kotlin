package com.example.blog

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

// Controller for managing articles via API
@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

	// Retrieve all articles and return them sorted by added date in descending order
	@GetMapping("/")
	fun findAll() = repository.findAllByOrderByAddedAtDesc()

	// Retrieve a specific article by slug
	@GetMapping("/{slug}")
	fun findOne(@PathVariable slug: String) =
		repository.findBySlug(slug) ?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
}

// Controller for managing users via API
@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

	// Retrieve all users
	@GetMapping("/")
	fun findAll(): MutableIterable<User> = repository.findAll()

	// Retrieve a specific user by login
	@GetMapping("/{login}")
	fun findOne(@PathVariable login: String) =
		repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}
