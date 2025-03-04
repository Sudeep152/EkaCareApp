package com.shashank.ekacareapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_articles")
data class NewsArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val content: String?,
    val publishedAt: String?
)