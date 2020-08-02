package com.example.theringingapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "method_table")
data class Method(
    @PrimaryKey @ColumnInfo(name = "notation") val notation: String,
    @ColumnInfo(name = "proper") val proper: String,
    @ColumnInfo(name = "num_of_bells") val numOfBells: Int
)