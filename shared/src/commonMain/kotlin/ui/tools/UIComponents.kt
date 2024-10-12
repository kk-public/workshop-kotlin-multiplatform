package ui.tools

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

object UIComponents {

    @Composable
    fun TextInput(
        value: String,
        onValueChange: (String) -> Unit,
        enabled: Boolean = true,
        label: String,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColorsPalette.current.tint,
                unfocusedBorderColor = AppColorsPalette.current.utility400,
                focusedLabelColor = AppColorsPalette.current.tint,
                cursorColor = AppColorsPalette.current.tint,
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            label = { Text(label) },
            maxLines = 1,
            enabled = enabled,
            keyboardOptions = keyboardOptions
        )
    }
}