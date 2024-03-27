package org.intellibear.quoteapp.quote.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("_id")
    val id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
)
