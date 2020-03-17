package com.ybbae.diaryofservant.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.Clip
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.ybbae.diaryofservant.R
import com.ybbae.diaryofservant.ui.blog.BlogScreen
import com.ybbae.diaryofservant.ui.home.HomeScreen
import com.ybbae.diaryofservant.ui.photo.TimelineScreen
import com.ybbae.diaryofservant.ui.shoping.ShopingScreen

@Composable
fun AppMain()
{
	val (drawerState, onDrawerStateChange) = state(
		init = {DrawerState.Closed},
		areEquivalent = {old, new -> old===new }
	)

	MaterialTheme(
		colors = lightThemeColors,
		typography = themeTypography
	) {
		ModalDrawerLayout(
			drawerState = drawerState,
			onStateChange = onDrawerStateChange,
			gesturesEnabled = drawerState == DrawerState.Opened,
			drawerContent = {
				AppDrawer(
					currentScreen = AppStatus.currentScreen,
					closeDrawer = {
						onDrawerStateChange(DrawerState.Closed)
					})
			},
			bodyContent = {
				AppContent {
					onDrawerStateChange(DrawerState.Opened)
				}
			}
		)
	}
}

@Composable
private fun AppContent(openDrawer: () -> Unit) {
	Crossfade(AppStatus.currentScreen) { screen ->
		Surface(color = (MaterialTheme.colors()).background) {
			when (screen) {
				is DSScreen.DSHomeScreen -> HomeScreen { openDrawer() }
				is DSScreen.DSShoppingScreen -> ShopingScreen { openDrawer() }
				is DSScreen.DSBlogScreen -> BlogScreen { openDrawer() }
				is DSScreen.DSPhotoScreen -> TimelineScreen { openDrawer() }
			}
		}
	}
}

@Composable
private fun AppDrawer(
	currentScreen: DSScreen,
	closeDrawer: () -> Unit
) {
	Column(modifier = LayoutSize.Fill) {
		Container(modifier = LayoutSize(96.dp, 96.dp) + LayoutPadding(16.dp)) {
			Clip(shape = RoundedCornerShape(8.dp)) {
				VectorImage(id = R.drawable.ic_launcher_foreground)
			}
		}
		Text(
			text = "빈땡",
			modifier = LayoutPadding(16.dp, 8.dp, 0.dp, 0.dp),
			style = themeTypography.h4
		)
		Text(text = "Green Level", modifier = LayoutPadding(16.dp, 0.dp, 0.dp, 16.dp))
		Divider(color = Color(0x14333333))
		DrawerButton(
			icon = R.drawable.ic_home,
			label = "홈",
			isSelected = currentScreen == DSScreen.DSHomeScreen,
			action = {
				navigateTo(DSScreen.DSHomeScreen)
				closeDrawer()
			}
		)
		DrawerButton(
			icon = R.drawable.ic_home,
			label = "쇼핑",
			isSelected = currentScreen == DSScreen.DSShoppingScreen,
			action = {
				navigateTo(DSScreen.DSShoppingScreen)
				closeDrawer()
			}
		)
		DrawerButton(
			icon = R.drawable.ic_home,
			label = "사진",
			isSelected = currentScreen == DSScreen.DSPhotoScreen,
			action = {
				navigateTo(DSScreen.DSPhotoScreen)
				closeDrawer()
			}
		)
		DrawerButton(
			icon = R.drawable.ic_home,
			label = "캣로그",
			isSelected = currentScreen == DSScreen.DSBlogScreen,
			action = {
				navigateTo(DSScreen.DSBlogScreen)
				closeDrawer()
			}
		)
	}
}

@Composable
private fun DrawerButton(
	modifier: Modifier = Modifier.None,
	@DrawableRes icon: Int,
	label: String,
	isSelected: Boolean,
	action: () -> Unit
) {
	val colors = MaterialTheme.colors()
	val textIconColor = if (isSelected) {
		colors.primary
	} else {
		colors.onSurface.copy(alpha = 0.6f)
	}

	val backgroundColor = if (isSelected) {
		colors.primary.copy(alpha = 0.12f)
	} else {
		colors.surface
	}

	Button(
			modifier = modifier + LayoutPadding(
				start = 8.dp,
				top = 8.dp,
				end = 8.dp
			),
			onClick = action,
			backgroundColor = backgroundColor,
			shape = RoundedCornerShape(4.dp),
			elevation = 0.dp
		) {
			Row(arrangement = Arrangement.Start) {
				VectorImage(
					modifier = LayoutGravity.Center,
					id = icon,
					tint = textIconColor
				)
				Spacer(modifier = LayoutWidth(16.dp))
				Text(
					text = label,
					style = (MaterialTheme.typography()).body2.copy(
						color = textIconColor
					),
					modifier = LayoutWidth.Fill
				)
			}
		}
}

@Preview
@Composable
fun Preview()
{
	MaterialTheme(colors = lightThemeColors) {
		AppDrawer(currentScreen = DSScreen.DSPhotoScreen, closeDrawer = {})
	}
}