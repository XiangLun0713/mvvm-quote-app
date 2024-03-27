package org.intellibear.quoteapp.global.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.intellibear.quoteapp.global.Event
import org.intellibear.quoteapp.global.EventBus

suspend fun ViewModel.sendEvent(event: Event) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}