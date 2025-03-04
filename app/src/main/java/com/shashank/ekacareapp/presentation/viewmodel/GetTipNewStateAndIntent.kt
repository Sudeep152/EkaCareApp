package com.shashank.ekacareapp.presentation.viewmodel

import androidx.paging.PagingData
import com.shashank.ekacareapp.domain.models.response.Article

sealed class NewsEvent {
    object GetNews : NewsEvent()
}
