package com.poc.presentation.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.poc.domain.model.post.Post
import com.poc.presentation.theme.graySurface

@Composable
fun PostListScreen(
    navController: NavController,
    postListViewModel: PostListViewModel = hiltViewModel()
) {
    val postState by postListViewModel.postState.collectAsState()

    when (postState) {
        is PostsState.LoadingState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is PostsState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (postState as PostsState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is PostsState.Success -> {
            val postList = (postState as PostsState.Success).posts

            if (postList != null) {
                LazyColumn {
                    items(postList.count()) {
                        PostItem(it = postList[it]) {
                            navController.navigate("postdetail/${it}")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostItem(it: Post, item: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = graySurface,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = { item.invoke(it.id) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            CircularImage(50.0, 50.0, 25.0, it.owner.picture)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "${it.owner.title.toUpperCase()}. ${it.owner.firstName} ${it.owner.lastName}",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "About: ${it.text}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
fun CircularImage(width: Double, height: Double, radius: Double, imageUrl: String) {
    Card(
        modifier = Modifier
            .width(width = width.dp)
            .height(height = height.dp), shape = RoundedCornerShape(radius.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl), contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
