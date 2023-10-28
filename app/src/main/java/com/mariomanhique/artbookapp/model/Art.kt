package com.mariomanhique.artbookapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mariomanhique.artbookapp.util.Constants.ART_BOOK_TABLE

@Entity(tableName = ART_BOOK_TABLE)
data class Art(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "artImage")
    val artImage:String,
    @ColumnInfo(name = "artistName")
    val artistName:String,
    @ColumnInfo(name = "year")
    val year:String
)
