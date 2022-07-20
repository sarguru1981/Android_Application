package com.poc.featureapp

//import com.poc.presentation.screens.users.UserListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.poc.featureapp.navigation.NavigationRouter
import com.poc.featureapp.ui.theme.FeatureAppTheme
import com.poc.presentation.post.PostListScreen
import com.poc.presentation.postdetail.PostDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeatureAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    PostsApp {
                        NavHost(
                            navController = navController,
                            startDestination = NavigationRouter.PostList.route
                        ) {
                            composable(NavigationRouter.PostList.route) {
                                PostListScreen(navController = navController)
                            }

                            composable(NavigationRouter.PostDetails.route) {
                                PostDetailScreen()
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun PostsApp(content: @Composable () -> Unit) {
    content()
}
