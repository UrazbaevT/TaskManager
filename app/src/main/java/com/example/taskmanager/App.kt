package com.example.taskmanager

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.taskmanager.data.db.AppDatabase
import com.google.firebase.FirebaseApp.initializeApp
import com.google.firebase.auth.FirebaseAuth

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