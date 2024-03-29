package org.intellibear.quoteapp.quote.data.repositories

import arrow.core.Either
import org.intellibear.quoteapp.quote.data.network.QuoteApi
import org.intellibear.quoteapp.quote.data.network.utils.ApiError
import org.intellibear.quoteapp.quote.domain.models.Quote
import org.intellibear.quoteapp.quote.domain.repositories.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : QuoteRepository {
    override suspend fun getRandomQuote(): Either<ApiError, Quote> = quoteApi.getRandomQuote()
}