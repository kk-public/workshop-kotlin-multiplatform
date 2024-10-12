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
                    name = "John Doe",
                    phone = "+994555555555",
                ),
                cards = listOf(
                    Card(
                        id = "1",
                        number = "1234 5678 9012 3456",
                        balance = 1000.0,
                        currency = Currency.AZN,
                        network = Card.Network.VISA,
                        status = Card.Status.ACTIVE,
                        currencyCode = "AZN"
                    ),
                    Card(
                        id = "2",
                        number = "1234 5678 9012 3456",
                        balance = 1000.0,
                        currency = Currency.AZN,
                        network = Card.Network.VISA,
                        status = Card.Status.ACTIVE,
                        currencyCode = "AZN"
                    )
                ),
                selectedCard = null,
                onSelect = {},
                transactions = listOf(
                    Transaction(
                        id = "1",
                        amount = 100.0,
                        currency = Currency.AZN,
                        date = LocalDateTime.parse("2022-02-22T20:26:55.748Z"),
                        description = "Payment",
                        category = "Shopping",
                        currencyCode = "AZN"
                    )
                ),
                onLogout = {}
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