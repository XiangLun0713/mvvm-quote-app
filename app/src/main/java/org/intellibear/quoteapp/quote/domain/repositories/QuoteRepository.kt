package org.intellibear.quoteapp.quote.domain.repositories

import arrow.core.Either
import org.intellibear.quoteapp.quote.data.utils.ApiError
import org.intellibear.quoteapp.quote.domain.models.Quote

interface QuoteRepository {
    suspend fun getRandomQuote(): Either<ApiError, Quote>
}