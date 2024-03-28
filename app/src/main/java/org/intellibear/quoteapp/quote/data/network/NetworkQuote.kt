package org.intellibear.quoteapp.quote.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.intellibear.quoteapp.quote.domain.models.Quote

@Serializable
data class NetworkQuote(
    @SerialName("_id")
    val id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
)

fun NetworkQuote.toQuote(): Quote = Quote(
    id = this.id,
    content = this.content,
    author = this.author,
    tags = this.tags
)