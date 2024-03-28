package org.intellibear.quoteapp.quote.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.intellibear.quoteapp.global.Event
import org.intellibear.quoteapp.global.utils.sendEvent
import org.intellibear.quoteapp.quote.data.network.utils.ApiError
import org.intellibear.quoteapp.quote.domain.repositories.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        getQuote()
    }

    fun getQuote() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            quoteRepository.getRandomQuote()
                .onRight { quote ->
                    _state.update {
                        it.copy(quote = quote)
                    }
                }.onLeft { error ->
                    val message: String = when (error) {
                        is ApiError.HttpException -> "Error ${error.code}"
                        ApiError.ConnectivityException -> "Connectivity Error: Please try reconnecting to the internet."
                        ApiError.SerializationException -> "Serialization Error"
                        ApiError.UnknownException -> "Unknown Error"
                    }
                    sendEvent(Event.Toast(message))
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}