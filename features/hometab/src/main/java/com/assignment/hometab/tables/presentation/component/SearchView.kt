package com.assignment.hometab.tables.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.theme.theme.AppTheme

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        enabled = false,
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Search",
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = AppTheme.dimens.bodyMedium
                    ),
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp).
            background(
                color =  AppTheme.colors.fieldColor
            )
            .clickable { onClick() },
    )
    }
