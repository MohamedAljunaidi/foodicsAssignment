package com.assignment.hometab.tables.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.hometab.tables.domain.model.Categories
import com.assignment.theme.theme.AppTheme

@Composable
fun CategoryTabs(
    categories: List<Categories>? = null,
    onTabSelected: (Int) -> Unit = {}
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = AppTheme.colors.selectedColor
            )
        }
    ) {
        categories?.forEachIndexed { index, category ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    category.id?.let { onTabSelected(it) }
                },
                text = {
                    Text(
                        text = category.name ?: "",
                        fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                        color = AppTheme.colors.black,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = AppTheme.dimens.titleMedium
                        ),
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun TableListScreenPreview() {
    CategoryTabs()
}