package com.example.moreactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddNote(
    initialNote: String = "",
    onDismissRequest: () -> Unit,
    onSave: (String) -> Unit
) {
    // State to track the note's value
    var note by remember { mutableStateOf(initialNote) }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Title
                Text(
                    text = "Add Note",
                    style = MaterialTheme.typography.headlineMedium
                )

                // TextField to enter a note
                TextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Add a note") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Close and Save buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = onDismissRequest) {
                        Text("Close")
                    }
                    Button(
                        onClick = {
                            onSave(note) // Call the save callback with the current note
                            onDismissRequest() // Dismiss the dialog
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}