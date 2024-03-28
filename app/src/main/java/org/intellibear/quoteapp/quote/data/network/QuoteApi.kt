package org.intellibear.quoteapp.quote.data.network

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.parameters
import org.intellibear.quoteapp.quote.data.network.utils.ApiError
import org.intellibear.quoteapp.quote.data.network.utils.HttpRoutes
import org.intellibear.quoteapp.quote.data.network.utils.toApiError
import org.intellibear.quoteapp.quote.domain.models.Quote
import javax.inject.Inject

class QuoteApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getRandomQuote(): Either<ApiError, Quote> {
        return Either.catch {
            val networkQuotes: List<NetworkQuote> = client.get {
                url(HttpRoutes.RANDOM_QUOTE)
                parameters {
                    parameter("limit", 1)
                    parameter("maxLength", 160)
                }
            }.body()
            networkQuotes.map { networkQuote -> networkQuote.toQuote() }.first()
        }.mapLeft { it.toApiError() }
    }
}