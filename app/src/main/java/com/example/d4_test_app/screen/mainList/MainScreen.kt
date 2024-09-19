package com.example.d4_test_app.screen.mainList

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.d4_test_app.NavigationItem
import com.example.d4_test_app.NavigationViewModel
import com.example.d4_test_app.remote.response.Movie
import timber.log.Timber


@Composable
@Preview
fun MoviePriew() {

}

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel,
    navigationViewModel: NavigationViewModel
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    MovieList(movies = movies,
        onClickItem = { movie ->
            navigationViewModel.setSelectedMovie(movie)
            navController.navigate(NavigationItem.DetailsInfo.route)
        })

}

@Composable
fun MovieList(movies: LazyPagingItems<Movie>, onClickItem: (movie: Movie) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(movies) { movie ->
            movie?.let {
                MovieItem(movie = movie, onClickItem = onClickItem)
            }
            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // Show initial loading indicator
                        this@LazyVerticalGrid.item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingItem()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        // Show loading indicator at the end of the list
                        this@LazyVerticalGrid.item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingItem()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        // Handle pagination error
                    }
                }
            }

        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClickItem: (movie: Movie) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClickItem(movie)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
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
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
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
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CaverPoster(drawableUrl: String?, height: Dp,with: Dp) {
    Surface(
        modifier = Modifier
            .width(with)
            .height(height)
            .padding(4.dp),

    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500$drawableUrl",
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Inside
        )
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) {
    items(count = items.itemCount) { index ->
        itemContent(items[index])
    }
}