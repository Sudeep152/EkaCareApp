package com.shashank.ekacareapp.presentation.routes

import android.net.Uri


sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Saved : Routes("saved")
    object NewsDetail : Routes("news_detail/{newsUrl}/{title}/{description}") {
        fun createRoute(newsUrl: String, title: String, description: String) =
            "news_detail/${Uri.encode(newsUrl)}/${Uri.encode(title)}/${Uri.encode(description)}"
    }
}