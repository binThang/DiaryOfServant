package com.ybbae.diaryofservant.ui

import androidx.compose.Model

sealed class DSScreen {
	object DSHomeScreen : DSScreen()
	object DSShoppingScreen : DSScreen()
	object DSPhotoScreen : DSScreen()
	object DSBlogScreen : DSScreen()
}

@Model
object AppStatus {
	var currentScreen: DSScreen = DSScreen.DSHomeScreen
}

fun navigateTo(destination: DSScreen) {
	AppStatus.currentScreen = destination
}