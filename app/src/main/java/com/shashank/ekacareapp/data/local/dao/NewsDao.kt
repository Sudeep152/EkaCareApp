package com.shashank.ekacareapp.data.local.dao

import androidx.room.*
import com.shashank.ekacareapp.data.local.entity.NewsArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: NewsArticleEntity)

    @Delete
    suspend fun deleteNews(article: NewsArticleEntity)

    @Query("SELECT * FROM news_articles ORDER BY publishedAt DESC")
    fun getSavedNews(): Flow<List<NewsArticleEntity>>
}