package co.cmd.cook.framework.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import co.cmd.cook.framework.Error
import co.cmd.core.data.datasource.AuthDatasource
import co.cmd.core.domain.ClientID
import co.cmd.core.domain.ClientSecretKey
import co.cmd.core.domain.Token
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

/**
 * Using SOLID principles. I = interface segregation. Should we add getToken/saveToken with fetchToken ?
 * May be not, should be in different Datasources.
 */

private const val TOKEN_PREFERENCE = "token-preference"

class LocalAuthDatasource(private val dataStore: DataStore<Preferences>) : AuthDatasource {

    override suspend fun fetchToken(clientID: ClientID, clientSecretKey: ClientSecretKey): Result<Token> =
        Result.failure(Error.Api("Generate token locally is not implemented"))

    override suspend fun getToken(): Result<Token> {
        val token = dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(TOKEN_PREFERENCE)]
        }.firstOrNull()
        return token?.let {
            Result.success(Token(it))
        } ?: run {
            Result.failure(Error.Api(""))
        }
    }

    override suspend fun saveToken(token: Token) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(TOKEN_PREFERENCE)] = token.value
        }
    }
}