import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.moreactions.AddDiscount
import com.example.moreactions.AddNote

@Composable
fun MoreActionsDialog(onDismissRequest: () -> Unit = {}) {
    var showDiscountDialog by remember { mutableStateOf(false) }
    var selectedPercentage by remember { mutableStateOf("") }
    var showAddNoteDialog by remember { mutableStateOf(false) }
    var savedNote = ""
    var showReferenceCodeDialog by remember { mutableStateOf(false) }

    // Main dialog
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // Title
                Text(
                    text = "More Actions",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Row of Icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ActionIcon(
                        icon = Icons.Default.ShoppingCart,
                        label = "Apply discount",
                        onClick = { showDiscountDialog = true }
                    )
                    ActionIcon(
                        icon = Icons.Default.Info,
                        label = "Add note",
                        onClick = { showAddNoteDialog = true }
                    )
                }

                // Clickable Tiles
                ListTile("Sale attribution") { /* Handle click */ }
                ListTile("Reference code") { showReferenceCodeDialog = true }
            }
        }
    }

    // Show individual dialogs
    if (showDiscountDialog) {
        AddDiscount(
            onDismissRequest = {
                showDiscountDialog = false
                               },
            onSave = {
                selectedPercentage = it
            }
        )
    }

    // Show AddNote dialog
    if (showAddNoteDialog) {
        AddNote(
            initialNote = savedNote, // Optional: prefill with the saved note
            onDismissRequest = { showAddNoteDialog = false },
            onSave = { note ->
                savedNote = note // Update the saved note
            }
        )
    }

    if (showReferenceCodeDialog) {
        ReferenceCode(onDismissRequest = { showReferenceCodeDialog = false })
    }
}


@Composable
fun ReferenceCode(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Reference Code", style = MaterialTheme.typography.headlineMedium)
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Enter reference code") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismissRequest) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun ActionIcon(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ListTile(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Info, // Replace with the correct icon
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun RadioButtonOption(label: String, percentage: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = false, onClick = {})
        Text(text = "$label ($percentage)", modifier = Modifier.padding(start = 8.dp))
    }
}
