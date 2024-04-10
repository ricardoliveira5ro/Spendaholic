package com.roliveira.spendaholic.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.roliveira.spendaholic.ProtoCategories
import com.roliveira.spendaholic.ProtoCurrency
import com.roliveira.spendaholic.ProtoSettings
import com.roliveira.spendaholic.model.Category
import com.roliveira.spendaholic.model.Currency
import com.roliveira.spendaholic.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SettingsDataStoreMapper(private val application: Application) {
    private val Context.protoDataStore: DataStore<ProtoSettings> by dataStore(
        fileName = "settings.pb",
        serializer = SettingsSerializer
    )

    companion object {
        @Volatile
        private var INSTANCE: SettingsDataStoreMapper? = null

        fun getInstance(application: Application): SettingsDataStoreMapper {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsDataStoreMapper(application)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun saveSettings(settings: Settings) : Flow<Boolean> {
        return flow {
            application.protoDataStore.updateData { store ->
                store.toBuilder()
                    .setCurrency(settings.currency.toProtoCurrency())
                    .clearCategories()
                    .addAllCategories(settings.categories.map { it.toProtoCategories() })
                    .build()
            }
            emit(true)
        } .catch { error ->
            Log.e("SettingsDataStoreHelper", "Error saving settings to datastore: ´saveSettings´: ${error.message}", error)
            emit(false)
        }
    }

    fun loadSettings(): Flow<Settings> {
        return application.protoDataStore.data.map { store ->
            store.toSettings()
        }
    }

    private fun ProtoSettings.toSettings(): Settings {
        return Settings(
            currency = this.currency.toCurrency(),
            categories = this.categoriesList.map { it.toCategory() }
        )
    }

    private fun Currency.toProtoCurrency(): ProtoCurrency {
        return ProtoCurrency.newBuilder()
            .setId(this.id)
            .setName(this.name)
            .setSymbol(this.symbol)
            .build()
    }

    private fun ProtoCurrency.toCurrency(): Currency {
        return Currency(
            id = this.id,
            name = this.name,
            symbol = this.symbol
        )
    }

    private fun Category.toProtoCategories(): ProtoCategories {
        return ProtoCategories.newBuilder()
            .setId(this.id)
            .setName(this.name)
            .setIcon(this.icon)
            .setBackgroundColor(colorToHex(this.backgroundColor))
            .build()
    }

    private fun ProtoCategories.toCategory(): Category {
        return Category(
            id = this.id,
            name = this.name,
            icon = this.icon,
            backgroundColor = Color(android.graphics.Color.parseColor(this.backgroundColor))
        )
    }

    private fun colorToHex(color: Color): String {
        return String.format("#%06X", (0xFFFFFF and color.toArgb()))
    }
}