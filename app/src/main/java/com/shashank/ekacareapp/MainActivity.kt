package com.shashank.ekacareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shashank.ekacareapp.presentation.screens.MainScreen
import com.shashank.ekacareapp.ui.theme.EkaCareAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EkaCareAppTheme {
                MainScreen()
            }
        }
    }
}
