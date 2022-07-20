package com.poc.featureapp.navigation

sealed class NavigationRouter(val route: String) {
    object PostList : NavigationRouter("PostList")
    object PostDetails : NavigationRouter("postdetail/{blogId}")
}
