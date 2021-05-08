package com.example.ayagini_yorganina_gore_uzat.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Urun::class],version = 1, exportSchema = false)
abstract class Database:RoomDatabase() {
    abstract fun userDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: com.example.ayagini_yorganina_gore_uzat.room_database.Database? = null

        fun getDatabase(context: Context): com.example.ayagini_yorganina_gore_uzat.room_database.Database{
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.ayagini_yorganina_gore_uzat.room_database.Database::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}