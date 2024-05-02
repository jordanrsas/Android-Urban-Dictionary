package com.cjra.urbandictionary.framework.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cjra.urbandictionary.framework.local.entity.WordDefinitionEntity

@Dao
interface WordDefinitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordDefinitions(definitions: List<WordDefinitionEntity>)

    @Query("DELETE FROM WordDefinitionEntity WHERE word = :word")
    suspend fun deleteWordDefinitionsByWord(word: String)

    @Query("DELETE FROM WordDefinitionEntity WHERE word IN (:words)")
    suspend fun deleteWordDefinitionsByWords(words: List<String>)

    @Query("SELECT * FROM WordDefinitionEntity WHERE word LIKE '%' || :word  || '%'")
    suspend fun getWordDefinitions(word: String): List<WordDefinitionEntity>
}