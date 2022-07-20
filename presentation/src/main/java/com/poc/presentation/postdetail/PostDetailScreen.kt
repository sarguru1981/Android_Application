package com.poc.presentation.postdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.poc.domain.model.post.Post
import com.poc.presentation.post.PostItem
import com.poc.presentation.theme.AnimatingFabContent
import com.poc.presentation.theme.baselineHeight
import com.poc.presentation.theme.purple500


@Composable
fun PostDetailScreen(postDetailsViewModel: PostDetailsViewModel = hiltViewModel()) {
    val postDetails by postDetailsViewModel.details.collectAsState()

    when (postDetails) {
        is PostDetailState.LoadingState -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is PostDetailState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (postDetails as PostDetailState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is PostDetailState.Success -> {
            val postList = (postDetails as PostDetailState.Success).data

            if (postList != null) {
                postList?.let {
                    Column(modifier = Modifier) {
                        PostItem(it = it, item = {})
                        DisplayItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayItem(post: Post) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                ) {
                    DisplayItemHeader(scrollState, post, this@BoxWithConstraints.maxHeight)
                    DisplayContent(post, this@BoxWithConstraints.maxHeight)
                }
            }
            AdoptMeButton(
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun AdoptMeButton(extended: Boolean, modifier: Modifier) {
    FloatingActionButton(
        onClick = { /* TODO */ },
        modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = purple500,
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Adopt Me"
                )
            },
            text = {
                Text(
                    text = "Adopt Me",
                )
            },
            extended = extended

        )
    }
}

@Composable
private fun DisplayItemHeader(
    scrollState: ScrollState,
    post: Post,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = rememberImagePainter(data = post.image),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun DisplayContent(post: Post, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
            Text(
                text = post.text.toString().toUpperCase(),
                modifier = Modifier.baselineHeight(32.dp),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }

        DisplayContentProperty(
            "Booked by: ",
            "${post.tags[0]}, ${post.tags[1]}, ${post.tags[2]}"
        )

        DisplayContentProperty("Likes: ", post.likes.toString())

        DisplayContentProperty("Published on: ", post.publishDate)

        // Add a spacer that always shows part (320.dp) of the fields list regardless of the device,
        // in order to always leave some content at the top.
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
fun DisplayContentProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}
