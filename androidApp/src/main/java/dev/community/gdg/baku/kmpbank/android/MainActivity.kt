package dev.community.gdg.baku.kmpbank.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Currency
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import kotlinx.datetime.LocalDateTime
import ui.flows.app.AppView
import ui.flows.auth.AuthContentView
import ui.flows.main.MainContentView
import ui.tools.AppColorsPalette
import ui.tools.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppView()
        }
    }
}


@PreviewLightDark
@Composable
fun DefaultMainPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColorsPalette.current.background
        ) {
            MainContentView(
                customer = Customer(
                    id = "1",
                    name = "User",
                    phone = "+994555555555",
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun DefaultAuthPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColorsPalette.current.background
        ) {
            AuthContentView(
                email = "",
                password = "",
                updateEmail = {},
                updatePassword = {},
                isLoading = false,
                login = {}
            )
        }
    }
}