package com.mariomanhique.artbookapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mariomanhique.artbookapp.utils.Constants.ART_BOOK_TABLE

@Entity(tableName = ART_BOOK_TABLE)
data class ArtBook(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "artImage")
    val artImage:String,
    @ColumnInfo(name = "artistName")
    val artistName:String,
    @ColumnInfo(name = "year")
    val year:String
)
