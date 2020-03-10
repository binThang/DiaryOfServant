package com.ybbae.diaryofservant.ui

import androidx.compose.Model

sealed class Screen {
	object Home : Screen()
	object CatShoping : Screen()
	object CatPhoto : Screen()
	object CatBlog : Screen()
}

@Model
object AppStatus {
	var currentScreen: Screen = Screen.Home
}

fun navigateTo(destination: Screen) {
	AppStatus.currentScreen = destination
}