package com.shashank.ekacareapp.domain.usecase

import com.shashank.ekacareapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    suspend operator fun invoke() = repository.getNews()
}