package com.assignment.theme.component

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.assignment.theme.R
import com.assignment.theme.theme.AppTheme
import com.assignment.theme.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    modifier: Modifier = Modifier,
    query: String? = "",
    onQueryChange: (String) -> Unit,
    onSearchClick: (() -> Unit)? = null,
    enabled: Boolean = true
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query ?: "",
                onQueryChange = onQueryChange,
                onSearch = onQueryChange,
                expanded = false,
                enabled = enabled,
                onExpandedChange = { },
                placeholder = { Text(stringResource(id = R.string.hint_search)) },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = AppTheme.colors.black,
                    unfocusedTextColor = Color.Gray,
                    cursorColor = AppTheme.colors.black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        },
        expanded = false,
        onExpandedChange = { },
        modifier = modifier.clickable {
            onSearchClick?.invoke()
        },
        shape = Shapes.medium
    ) {}
}
