package org.intellibear.quoteapp.global.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(isLoading: Boolean) {
    if (isLoading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            ElevatedCard(
                modifier = Modifier.clip(CircleShape),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}