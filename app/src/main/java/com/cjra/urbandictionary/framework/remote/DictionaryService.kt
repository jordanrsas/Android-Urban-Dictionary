package com.cjra.urbandictionary.framework.remote

import com.cjra.urbandictionary.application.data.ApiKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

data class ApiResponse(val list: List<DefinitionRaw>)
class DictionaryService(private val api: DictionaryApi) {

    suspend fun defineWord(word: String): Flow<List<DefinitionRaw>> {
        return flow {
            val response = runCatching {
                api.defineWord(word)
            }.getOrElse {
                ApiResponse(emptyList())
            }
            emit(response.list)
        }
    }
}

interface DictionaryApi {
    @Headers(
        "X-RapidAPI-Key: ${ApiKeys.RAPID_API_KEY}",
        "X-RapidAPI-Host: ${ApiKeys.RAPID_API_HOST}"
    )
    @GET("define")
    suspend fun defineWord(@Query("term") term: String): ApiResponse
}

