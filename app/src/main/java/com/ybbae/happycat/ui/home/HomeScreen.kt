package com.ybbae.happycat.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.ybbae.happycat.R
import com.ybbae.happycat.ui.VectorImageButton
import com.ybbae.happycat.ui.lightThemeColors

@Composable
fun HomeScreen(openDrawer : () -> Unit)
{
	MaterialTheme(colors = lightThemeColors) {
		Column() {
			TopAppBar(title = { Text(text = "6시 내고양") },
				navigationIcon = {
					VectorImageButton(
						R.drawable.ic_launcher_foreground) {
					openDrawer()
				}
			})

			VerticalScroller() {
				Column(modifier = LayoutPadding(16.dp)) {
					Text(text = "Home")
				}
			}
		}
	}
}

@Composable
@Preview
fun preview()
{
	HomeScreen() {}
}