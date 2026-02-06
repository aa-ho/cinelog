package de.travelinski

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun BaseDialog(
    showDialog: Boolean,
    titleContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                    .shadow(2.dp, RoundedCornerShape(10)), shape = RoundedCornerShape(10)
            ) {
                LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                    stickyHeader {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background).padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            titleContent()
                        }
                    }
                    item {
                        bodyContent()
                    }
                }
            }
        }
    }
}
