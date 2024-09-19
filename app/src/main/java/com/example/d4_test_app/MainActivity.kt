package com.example.d4_test_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.d4_test_app.ui.theme.D4_TEST_APPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            D4_TEST_APPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .padding(vertical = 24.dp),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

