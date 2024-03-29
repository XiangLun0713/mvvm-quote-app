package org.intellibear.quoteapp.quote.domain.models

data class Quote(
    val id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
)
