package com.shashank.ekacareapp.presentation.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.shashank.ekacareapp.domain.models.response.Article
import com.shashank.ekacareapp.presentation.viewmodel.GetTopNewsViewModel

@Composable
fun NewsListScreen(navController: NavController) {
    val viewModel: GetTopNewsViewModel = hiltViewModel()
    val newsPagingItems: LazyPagingItems<Article> = viewModel.newsState.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(newsPagingItems.itemCount) { index ->
                newsPagingItems[index]?.let { article ->
                    NewsItem(article, navController = navController)
                }
            }
            newsPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingIndicator() }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem() }
                    }
                }
            }
        }
    }
}


@Composable
fun NewsItem(article: Article, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val encodedUrl = Uri.encode(article.url)
                val encodedTitle = Uri.encode(article.title)
                val encodedDescription = Uri.encode(article.description)
                val route = "news_detail/$encodedUrl/$encodedTitle/$encodedDescription"
                navController.navigate(route)
            },
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = article.urlToImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                contentDescription = ""
            )
            Text(
                text = article.title ?: "No Title",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.description ?: "No Description",
                fontSize = 14.sp,
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val encodedUrl = Uri.encode(article.url)
                    val encodedTitle = Uri.encode(article.title)
                    val encodedDescription = Uri.encode(article.description)
                    val route = "news_detail/$encodedUrl/$encodedTitle/$encodedDescription"
                    navController.navigate(route)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Read More")
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadingNextPageItem() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}