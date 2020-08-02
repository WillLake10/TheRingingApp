package com.example.theringingapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theringingapp.data.dto.Method

@Dao
interface MethodDao {
    @Query("SELECT * from method_table ORDER BY proper ASC")
    fun getAlphabetizedMethods(): LiveData<List<Method>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(method: Method)

    @Query("DELETE FROM method_table")
    suspend fun deleteAll()
}