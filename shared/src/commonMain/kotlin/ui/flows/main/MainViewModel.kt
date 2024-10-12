package ui.flows.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import ui.base.BaseViewModel

class MainViewModel(
) : BaseViewModel() {

    var customer: Customer? by mutableStateOf(null)

    // TODO: keep the record of synced card ids

    init {
        // TODO: observe customer
        // TODO: observe cards
    }

    fun selectCard(card: Card?) {
        // TODO: do not select the same card

        // TODO: reset transactions

        // TODO: sync the transactions (do not sync if already synced)

        // TODO: observe the card's transactions
    }

    fun loadData() {
        // TODO: load initial data

        // TODO: do not load data if previous job is not finished
    }
}