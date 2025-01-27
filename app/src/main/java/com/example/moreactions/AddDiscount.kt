package com.example.moreactions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddDiscount(onDismissRequest: () -> Unit) {
    // State to manage selected radio button
    var selectedOption by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Discount", style = MaterialTheme.typography.headlineMedium)

                // Radio buttons
                Column {
                    RadioButtonOption(
                        label = "New customer loyalty",
                        percentage = "5%",
                        selectedOption = selectedOption,
                        onSelect = { selectedOption = it }
                    )
                    RadioButtonOption(
                        label = "Christmas winter sale",
                        percentage = "12%",
                        selectedOption = selectedOption,
                        onSelect = { selectedOption = it }
                    )
                    RadioButtonOption(
                        label = "Summer sale",
                        percentage = "3%",
                        selectedOption = selectedOption,
                        onSelect = { selectedOption = it }
                    )
                }
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
                            // Save logic can go here
                            onDismissRequest()
                        }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonOption(
    label: String,
    percentage: String,
    selectedOption: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedOption == label,
            onClick = { onSelect(label) }
        )
        Text(text = "$label ($percentage)", modifier = Modifier.padding(start = 8.dp))
    }
}
