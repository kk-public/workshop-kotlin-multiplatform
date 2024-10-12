package ui.flows.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.tools.AppColorsPalette
import ui.tools.AppTheme
import ui.tools.UIComponents

@Composable
@Preview
fun AuthView(
    vm: AuthViewModel = koinViewModel<AuthViewModel>(),
    onAuthorized: () -> Unit
) {
    vm.onAuthorized = onAuthorized

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColorsPalette.current.background
        ) {
            AuthContentView(
                email = vm.email,
                password = vm.password,
                updateEmail = vm::updateEmail,
                updatePassword = vm::updatePassword,
                isLoading = vm.isLoading,
                login = vm::login
            )
        }
    }
}

@Composable
fun AuthContentView(
    email: String,
    password: String,
    isLoading: Boolean,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    login: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.padding(36.dp)
                .widthIn(max = 300.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "GDG Bank KMM",
                style = MaterialTheme.typography.labelLarge,
                color = AppColorsPalette.current.utility400,
            )
            Spacer(modifier = Modifier.height(16.dp))
            UIComponents.TextInput(
                value = email,
                onValueChange = {
                    updateEmail(it)
                },
                label = "Email",
                enabled = isLoading.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            UIComponents.TextInput(
                value = password,
                onValueChange = {
                    updatePassword(it)
                },
                label = "Password",
                enabled = isLoading.not(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )
            Button(
                onClick = {
                    login()
                },
                enabled = isLoading.not(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .height(48.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = AppColorsPalette.current.primary
                )
            ) {
                Text(
                    "Login",
                    style = MaterialTheme.typography.labelMedium,
                    color = AppColorsPalette.current.white
                )
            }
        }
    }
}