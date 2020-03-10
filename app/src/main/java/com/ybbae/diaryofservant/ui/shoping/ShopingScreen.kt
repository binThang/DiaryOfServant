package com.ybbae.diaryofservant.ui.shoping

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.material.Tab
import androidx.ui.material.TabRow
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import com.ybbae.diaryofservant.R
import com.ybbae.diaryofservant.ui.VectorImageButton

private enum class Sections(val title: String) {
	Feed("사료"),
	Sand("모래"),
	Toy("장난감"),
	Snack("간식"),
	Bath("목욕"),
	Scratchier("스크래쳐"),
	Cage("이동장")
}

@Composable
fun ShopingScreen(openDrawer : () -> Unit)
{
	var section by state(
		init = { Sections.Feed },
		areEquivalent = fun(old: Any?, new: Any?) = old === new
	)
	val sectionTitles = Sections.values().map { it.title }

	Column {
		TopAppBar(title = { Text(text = "쇼핑") }, navigationIcon = {
			VectorImageButton(id = R.drawable.ic_launcher_foreground, onClick = { openDrawer() })
		})
		TabRow(
			items = sectionTitles,
			selectedIndex = section.ordinal,
			scrollable = true
		) { index, text ->
			Tab(
				text = {Text(text=text)},
				selected = section.ordinal == index,
				onSelected = {
					section = Sections.values()[index]
			})
		}

		Container(modifier = LayoutFlexible(1f)) {
			ItemsTab(section)
		}
	}
}

@Composable
private fun ItemsTab(sections: Sections) {

}

@Preview
@Composable
fun preview()
{
	ShopingScreen() {}
}