package com.ybbae.diaryofservant.ui.photo

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.ybbae.diaryofservant.R
import com.ybbae.diaryofservant.ui.VectorImageButton
import com.ybbae.diaryofservant.ui.lightThemeColors

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
				Column(modifier = LayoutPadding(16.dp)) {
					Text(text = "Timeline")
				}
			}
		}
	}
}

@Preview
@Composable
fun Preview() {
	TimelineScreen(openDrawer = {})
}