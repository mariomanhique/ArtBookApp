package com.mariomanhique.artbookapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.mariomanhique.artbookapp.model.ArtBook
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtBookDao {


    @Query("select * from artBook_table")
    fun getAllArts():Flow<List<ArtBook>>

    @Query("select * from artBook_table where id =:artId")
    fun getArtById(artId: Int):Flow<ArtBook>

    @Update
    suspend fun updateArt(artBook: ArtBook)

    @Delete
    suspend fun deleteArts(artBook: ArtBook)

    @Query("delete from artBook_table where id =:artId")
    suspend fun deleteArtBookByID(artId:Int)


}