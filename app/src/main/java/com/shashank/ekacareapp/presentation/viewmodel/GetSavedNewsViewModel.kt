package com.shashank.ekacareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.ekacareapp.data.local.entity.NewsArticleEntity
import com.shashank.ekacareapp.domain.models.response.Article
import com.shashank.ekacareapp.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetSavedNewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _savedNews = MutableStateFlow<List<NewsArticleEntity>>(emptyList())
    val savedNews: StateFlow<List<NewsArticleEntity>> get() = _savedNews

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchSavedNews()
    }

    private fun fetchSavedNews() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getSavedNews().collect { news ->
                _savedNews.value = news
                _isLoading.value = false
            }
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            _isLoading.value = true
            val entity = NewsArticleEntity(
                url = article.url ?: "",
                title = article.title,
                description = article.description,
                imageUrl = article.urlToImage,
                content = article.content,
                publishedAt = article.publishedAt
            )
            repository.saveNews(entity)
            _isLoading.value = false
        }
    }

    fun deleteArticle(article: NewsArticleEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.deleteNews(article)
            _isLoading.value = false
        }
    }
}