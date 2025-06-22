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
import com.assignment.hometab.tables.presentation.TablesViewModel
import com.assignment.theme.theme.AppTheme

@Composable
fun CategoryTabs(
    categories: List<Categories>? = null,
    selectedTabIndex: Int,
    onTabSelected: (Pair<Int, Int>) -> Unit = {}
) {

    var tabIndex = selectedTabIndex

    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            if (tabPositions.isNotEmpty()){
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = AppTheme.colors.selectedColor
                )
            }
        }
    ) {
        categories?.forEachIndexed { index, category ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    tabIndex = index
                    category.id?.let { onTabSelected(Pair(index,it)) }
                },
                text = {
                    Text(
                        text = category.name ?: "",
                        fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Normal,
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
