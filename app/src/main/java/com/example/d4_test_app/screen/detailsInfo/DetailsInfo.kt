package com.example.d4_test_app.screen.detailsInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.d4_test_app.NavigationViewModel
import com.example.d4_test_app.screen.mainList.CaverPoster
import com.example.d4_test_app.screen.mainList.MainScreenViewModel
import com.example.d4_test_app.screen.mainList.MovieItem

@Composable
fun DetailsInfoScreen(navController: NavHostController,
                      navigationViewModel: NavigationViewModel
){
    val movie = navigationViewModel.selectedMovie.collectAsState().value
    Surface (modifier = Modifier.fillMaxSize()){
        movie?.let { movie->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    CaverPoster(movie.poster_path, 150.dp,100.dp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.bodyMedium,
                            
                        )
                        Text(
                            text = "Rating: ${movie.vote_average}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}