package org.intellibear.quoteapp.quote.data.repositories

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.parameters
import org.intellibear.quoteapp.quote.data.utils.ApiError
import org.intellibear.quoteapp.quote.data.utils.HttpRoutes
import org.intellibear.quoteapp.quote.data.utils.toApiError
import org.intellibear.quoteapp.quote.domain.models.Quote
import org.intellibear.quoteapp.quote.domain.repositories.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : QuoteRepository {
    override suspend fun getRandomQuote(): Either<ApiError, Quote> {
        return Either.catch {
            val quotes: List<Quote> = client.get {
                url(HttpRoutes.RANDOM_QUOTE)
                parameters {
                    parameter("limit", 1)
                    parameter("maxLength", 160)
                }
            }.body()
            quotes.first()
        }.mapLeft { it.toApiError() }
    }
}