package org.intellibear.quoteapp.quote.data.utils

sealed class ApiError {
    data class HttpException(val code: Int, val message: String): ApiError()
    data object ConnectivityException: ApiError()
    data object SerializationException: ApiError()
    data object UnknownException: ApiError()
}