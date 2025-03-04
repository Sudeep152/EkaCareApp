package com.shashank.ekacareapp.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shashank.ekacareapp.data.remote.api.NewsApi
import com.shashank.ekacareapp.domain.mapper.toDomain
import com.shashank.ekacareapp.domain.models.response.Article
import javax.inject.Inject

class NewsPagingSource @Inject constructor(private val apiService: NewsApi) :
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getTopHeadlines(page = page)
            val articles = response.articles?.mapNotNull { it?.toDomain() } ?: emptyList()
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}