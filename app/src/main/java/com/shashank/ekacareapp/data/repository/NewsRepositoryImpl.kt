package com.shashank.ekacareapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shashank.ekacareapp.data.local.dao.NewsDao
import com.shashank.ekacareapp.data.local.entity.NewsArticleEntity
import com.shashank.ekacareapp.data.remote.api.NewsApi
import com.shashank.ekacareapp.domain.repository.NewsPagingSource
import com.shashank.ekacareapp.domain.repository.NewsRepository
import com.shashank.ekacareapp.domain.models.response.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override suspend fun getNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 5, prefetchDistance = 1),
            pagingSourceFactory = {
                NewsPagingSource(apiService)
            }
        ).flow
    }

    override fun getSavedNews(): Flow<List<NewsArticleEntity>> {
        return newsDao.getSavedNews()
    }

    override suspend fun saveNews(article: NewsArticleEntity) {
        newsDao.insertNews(article)
    }

    override suspend fun deleteNews(article: NewsArticleEntity) {
        newsDao.deleteNews(article)
    }
}