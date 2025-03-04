package com.shashank.ekacareapp.domain.repository

import androidx.paging.PagingData
import com.shashank.ekacareapp.data.local.entity.NewsArticleEntity
import com.shashank.ekacareapp.domain.models.response.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews():Flow<PagingData<Article>>
    fun getSavedNews(): Flow<List<NewsArticleEntity>>
    suspend fun saveNews(article: NewsArticleEntity)
    suspend fun deleteNews(article: NewsArticleEntity)
}