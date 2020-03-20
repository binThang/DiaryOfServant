package com.ybbae.diaryofservant.ui.timeline

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.ybbae.diaryofservant.R
import com.ybbae.diaryofservant.ui.*

@Composable
fun TimelineScreen(openDrawer : () -> Unit) {
	MaterialTheme(colors = lightThemeColors) {
		Column() {
			TopAppBar(title = { Text(text = "사진") },
				navigationIcon = {
					VectorImageButton(
						R.drawable.ic_launcher_foreground) {
						openDrawer()
					}
				})

			VerticalScroller() {
				Column(modifier = LayoutPadding(16.dp) + LayoutFlexible(1f)) {
					Text(text = "Timeline")
				}
			}
			
			FloatingActionButton(
				onClick = {
					navigateTo(DSScreen.DSWriteCardScreen)
				},
				modifier = LayoutGravity.End + LayoutPadding(end = 16.dp)
			) {
				VectorImage(id = R.drawable.ic_home, tint = (MaterialTheme.colors()).background)
			}
		}
	}
}

@Preview
@Composable
fun Preview() {
	TimelineScreen() {}
}