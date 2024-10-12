package ui.flows.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.GetCustomerUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.LogoutUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.ObserveCustomerUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.card.ObserveCardsUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.card.SyncCardsUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.transaction.ObserveTransactionsByCardUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.transaction.SyncTransactionsByCardUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import ui.base.BaseViewModel

class MainViewModel(
    observeCardsUseCase: ObserveCardsUseCase,
    private val syncCardsUseCase: SyncCardsUseCase,
    observeCustomerUseCase: ObserveCustomerUseCase,
    private val syncCustomerUseCase: GetCustomerUseCase,
    private val syncTransactionsByCardUseCase: SyncTransactionsByCardUseCase,
    private val observeTransactionsByCardUseCase: ObserveTransactionsByCardUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    var customer: Customer? by mutableStateOf(null)

    var cards: List<Card> by mutableStateOf(emptyList())
        private set

    var transactions: List<Transaction> by mutableStateOf(emptyList())
        private set

    var selectedCard: Card? by mutableStateOf(null)

    private var syncedCardIds: Set<String> = emptySet()
    private var subscriptionJob: Job? = null

    var onLogout: (() -> Unit)? = null

    init {
        observeCardsUseCase.observe(Unit)
            .onEach {
                cards = it

                if (selectedCard == null || cards.contains(selectedCard).not()) {
                    selectCard(cards.firstOrNull())
                }
            }
            .launch()

        observeCustomerUseCase.observe(Unit)
            .onEach { customer = it }
            .launch()

        launchRoutineWithLoading({
            syncData()
        })
    }

    fun selectCard(card: Card?) {
        if (card == selectedCard) return

        selectedCard = card

        transactions = emptyList()
        subscriptionJob?.cancel()

        if (card == null) {
            return
        }

        if (syncedCardIds.contains(card.id).not()) {
            syncTransactionsByCardUseCase.launch(card) {
                onSuccess = {
                    syncedCardIds += card.id
                }
            }
        }

        subscriptionJob = observeTransactionsByCardUseCase.observe(card)
            .onEach { transactions = it }
            .launch()
    }

    fun logout() {
        logoutUseCase.launch(Unit) {
            onSuccess = {
                syncedCardIds = emptySet()
                onLogout?.invoke()
            }
        }
    }

    fun loadData() {
        if (isLoading.not() && selectedCard == null) {
            launchRoutineWithLoading({
                syncData()
            })
        }
    }

    private suspend fun syncData() {
        syncCardsUseCase.execute(Unit)
        syncCustomerUseCase.execute(Unit)
    }
}