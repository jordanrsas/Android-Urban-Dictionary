package com.cjra.urbandictionary.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cjra.urbandictionary.framework.local.entity.DefinitionEntity

@Database(
    entities = [DefinitionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DictionaryDataBase : RoomDatabase() {
    abstract val dao: DictionaryDao
}