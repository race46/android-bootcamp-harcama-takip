package com.example.ayagini_yorganina_gore_uzat.room_database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun ekleUrun(urun: Urun)

    @Query("select * from urun_listesi order by id asc")
    fun urunleriAl(): LiveData<List<Urun>>

    @Delete
    fun silUrun(urun: Urun)
}