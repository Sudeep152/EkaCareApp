package com.shashank.ekacareapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shashank.ekacareapp.domain.models.response.Article
import com.shashank.ekacareapp.domain.usecase.GetTopNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTopNewsViewModel @Inject constructor(
    private val getNewsUseCase: GetTopNewsUseCase
) : ViewModel() {

    private val _newsState: MutableStateFlow<PagingData<Article>> =
        MutableStateFlow(value = PagingData.empty())
    val newsState: StateFlow<PagingData<Article>> get() = _newsState

    init {
        onEvent(NewsEvent.GetNews)
    }

    private fun onEvent(event: NewsEvent) {
        viewModelScope.launch {
            when (event) {
                is NewsEvent.GetNews -> {
                    getMovies()
                }
            }
        }
    }

    private suspend fun getMovies() {
        getNewsUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _newsState.value = it
            }
    }
}