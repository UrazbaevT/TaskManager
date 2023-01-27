package com.example.taskmanager

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.taskmanager.data.db.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()
    }

    companion object{
        lateinit var db: AppDatabase
    }

}