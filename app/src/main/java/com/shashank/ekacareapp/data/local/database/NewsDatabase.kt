package com.shashank.ekacareapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shashank.ekacareapp.data.local.dao.NewsDao
import com.shashank.ekacareapp.data.local.entity.NewsArticleEntity

@Database(entities = [NewsArticleEntity::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}