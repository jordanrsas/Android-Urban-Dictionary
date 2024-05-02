package com.cjra.urbandictionary.framework.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cjra.urbandictionary.framework.local.entity.DefinitionEntity

@Dao
interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefinition(definitions: List<DefinitionEntity>)

    @Query("DELETE FROM definitionentity WHERE definition IN (:definitions)")
    suspend fun deleteDefinitions(definitions: List<String>) : Int

    @Query("SELECT * FROM definitionentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getDefinitions(word: String): List<DefinitionEntity>
}