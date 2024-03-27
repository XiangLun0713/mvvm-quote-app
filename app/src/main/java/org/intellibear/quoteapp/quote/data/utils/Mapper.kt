package org.intellibear.quoteapp.quote.data.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

fun Throwable.toApiError(): ApiError {
    return when (this) {
        is IOException -> ApiError.ConnectivityException

        is ServerResponseException -> ApiError.HttpException(
            this.response.status.value,
            this.message
        )

        is ClientRequestException -> ApiError.HttpException(
            this.response.status.value,
            this.message
        )

        is SerializationException -> ApiError.SerializationException

        else -> ApiError.UnknownException
    }
}