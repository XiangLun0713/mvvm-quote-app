package org.intellibear.quoteapp.quote.presentation.home_screen

import org.intellibear.quoteapp.quote.domain.models.Quote

data class HomeViewState(
    val isLoading: Boolean = false,
    val quote: Quote? = null,
)
