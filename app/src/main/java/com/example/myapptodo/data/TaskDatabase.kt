package com.example.myapptodo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase() : RoomDatabase() {

       abstract fun listDao(): ListDao

       companion object {
           @Volatile
           private var Instance: TaskDatabase? = null

           fun getDatabase(context: Context): TaskDatabase {
               val tempInstance = Instance
               if (tempInstance != null) {
                   return tempInstance
               }
               synchronized(this) {
                   val instance = Room.databaseBuilder(
                       context.applicationContext,
                       TaskDatabase::class.java,
                       "task_database"
                   ).build()
                   Instance = instance
                   return instance
               }
           }

       }


}