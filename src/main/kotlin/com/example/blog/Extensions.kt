package com.example.blog

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

// Extension function to format LocalDateTime as a custom string
fun LocalDateTime.format(): String = this.format(englishDateFormatter)

// Helper function to get the ordinal representation of a number (e.g., 1st, 2nd, 3rd)
private val daysLookup = (1..31).associate { it.toLong() to getOrdinal(it) }

// Date formatter for English locale with ordinal day representation
private val englishDateFormatter = DateTimeFormatterBuilder()
	.appendPattern("yyyy-MM-dd")
	.appendLiteral(" ")
	.appendText(ChronoField.DAY_OF_MONTH, daysLookup)
	.appendLiteral(" ")
	.appendPattern("yyyy")
	.toFormatter(Locale.ENGLISH)

// Function to get the ordinal representation of a number
private fun getOrdinal(n: Int) = when {
	n in 11..13 -> "${n}th"
	n % 10 == 1 -> "${n}st"
	n % 10 == 2 -> "${n}nd"
	n % 10 == 3 -> "${n}rd"
	else -> "${n}th"
}

// Function to convert a string to a slug
fun String.toSlug() = lowercase(Locale.getDefault())
	.replace("\n", " ")
	.replace("[^a-z\\d\\s]".toRegex(), " ")
	.split(" ")
	.joinToString("-")
	.replace("-+".toRegex(), "-")
