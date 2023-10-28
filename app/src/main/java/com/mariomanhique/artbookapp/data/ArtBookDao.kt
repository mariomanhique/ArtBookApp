package com.mariomanhique.artbookapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mariomanhique.artbookapp.model.Art

@Dao
interface ArtBookDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)
    @Query("select * from artBook_table")
    fun getAllArts():LiveData<List<Art>>

    @Query("select * from artBook_table where id =:artId")
    fun getArtById(artId: Int):LiveData<Art>

    @Update
    suspend fun updateArt(art: Art)

    @Delete
    suspend fun deleteArts(art: Art)

    @Query("delete from artBook_table where id =:artId")
    suspend fun deleteArtBookByID(artId:Int)


}