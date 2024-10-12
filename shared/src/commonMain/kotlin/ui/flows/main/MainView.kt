package ui.flows.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Currency
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import kmp_bank_client.shared.generated.resources.Res
import kmp_bank_client.shared.generated.resources.ic_mastercard
import kmp_bank_client.shared.generated.resources.ic_unionpay
import kmp_bank_client.shared.generated.resources.ic_visa
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ui.tools.AppColorsPalette
import ui.tools.AppTheme

@Composable
fun MainView(
    vm: MainViewModel = koinViewModel<MainViewModel>(),
    onLogout: () -> Unit
) {
    // TODO: handle logout callback

    // TODO: load initial data

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppColorsPalette.current.background
        ) {
            MainContentView(
                customer = vm.customer
            )
        }
    }
}

@Preview
@Composable
fun MainContentView(
    customer: Customer?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        customer?.let {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello, ${it.name}",
                    style = MaterialTheme.typography.titleLarge,
                    color = AppColorsPalette.current.utility500
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    onClick = {
                        // TODO: handle logout
                    }
                ) {
                    Text(
                        "Logout",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = AppColorsPalette.current.utility500
                    )
                }
            }

            // TODO: display cards

            // TODO: display transactions for selected cards
        }
    }
}

@Composable
fun CardView(
) {
    Box(
        modifier = Modifier
            .size(200.dp, 120.dp)
            .background(
                AppColorsPalette.current.surface300,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // TODO: display card details
    }
}

@Composable
fun TransactionView(transaction: Transaction) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(AppColorsPalette.current.surface200, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                transaction.description,
                style = MaterialTheme.typography.bodyMedium,
                color = AppColorsPalette.current.utility400
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                formatDate(transaction.date),
                style = MaterialTheme.typography.bodySmall,
                color = AppColorsPalette.current.utility300
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${transaction.amount} ${
                getCurrency(
                    transaction.currency,
                    transaction.currencyCode
                )
            }",
            style = MaterialTheme.typography.bodyLarge,
            color = AppColorsPalette.current.utility400,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

}

fun getIcon(network: Card.Network): DrawableResource? {
    return when (network) {
        Card.Network.VISA -> Res.drawable.ic_visa
        Card.Network.MASTERCARD -> Res.drawable.ic_mastercard
        Card.Network.UNIONPAY -> Res.drawable.ic_unionpay
        Card.Network.UNKNOWN -> null
    }
}

fun getCurrency(currency: Currency, code: String): String {
    return when (currency) {
        Currency.AZN -> "₼"
        Currency.USD -> "\$"
        Currency.EUR -> "€"
        Currency.UNKNOWN -> code
    }
}

fun formatDate(date: LocalDateTime): String {
    val dateFormat = LocalDateTime.Format {
        hour(padding = Padding.ZERO)
        char(':')
        minute(padding = Padding.ZERO)
        char(' ')
        monthNumber(padding = Padding.ZERO)
        char('/')
        dayOfMonth()
        char('/')
        year()
    }
    return dateFormat.format(date)
}
