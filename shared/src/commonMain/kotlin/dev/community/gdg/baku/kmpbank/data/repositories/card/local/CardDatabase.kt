@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package dev.community.gdg.baku.kmpbank.data.repositories.card.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.dto.CardLocalDTO
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.TransactionDAO
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.dto.TransactionLocalDTO

@Database(
    entities = [CardLocalDTO::class, TransactionLocalDTO::class],
    version = 1
)
@ConstructedBy(CardDatabaseConstructor::class)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDAO
    abstract fun transactionDAO(): TransactionDAO
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object CardDatabaseConstructor : RoomDatabaseConstructor<CardDatabase> {
    override fun initialize(): CardDatabase
}