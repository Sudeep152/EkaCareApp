package com.shashank.ekacareapp.domain.mapper

import com.shashank.ekacareapp.data.remote.models.response.ArticleDTO
import com.shashank.ekacareapp.data.remote.models.response.SourceDTO
import com.shashank.ekacareapp.domain.models.response.Article
import com.shashank.ekacareapp.domain.models.response.Source

fun ArticleDTO.toDomain(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source?.toDomain(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun SourceDTO.toDomain(): Source {
    return Source(id, name)
}