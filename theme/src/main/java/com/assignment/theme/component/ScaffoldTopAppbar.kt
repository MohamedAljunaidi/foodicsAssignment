package com.assignment.theme.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.theme.R
import com.assignment.theme.theme.AppTheme
import com.assignment.theme.theme.color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppbar(
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    title: String,
    onNavigationIconClick: () -> Unit,
    navigationIcon: Painter = rememberVectorPainter(image = Icons.AutoMirrored.Outlined.ArrowBack),
    snackbarHost: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = containerColor,
        contentColor = contentColor,
        snackbarHost = snackbarHost,
        topBar = {
            Surface(shadowElevation = 1.dp) {
                CenterAlignedTopAppBar(
                    title = {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    end = AppTheme.dimens.paddingMedium,
                                    top = AppTheme.dimens.paddingSmall,
                                    bottom = AppTheme.dimens.paddingSmall
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = AppTheme.dimens.titleLarge
                                )
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconWithText(painter = painterResource(id = R.drawable.ic_dinner), count = "03")
                                Spacer(
                                    modifier = Modifier
                                        .width(8.dp)
                                )
                                IconWithText(painter = painterResource(id = R.drawable.ic_group), count = "02")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.color.topAppBar,
                        navigationIconContentColor = MaterialTheme.color.black,
                        titleContentColor = MaterialTheme.color.black,
                        actionIconContentColor = MaterialTheme.color.black,
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                onNavigationIconClick.invoke()
                                delay(200)
                            }
                        }) {
                            Icon(
                                modifier = Modifier.size(AppTheme.dimens.imageSizeExtraSmall),
                                painter = navigationIcon,
                                contentDescription = "navigationIcon"
                            )
                        }
                    },
                )
            }
        },
        bottomBar = bottomBar,
        content = content
    )
}


@Composable
fun IconWithText(painter: Painter, count: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(AppTheme.dimens.imageSizeExtraSmall)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = count,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = AppTheme.dimens.titleMedium
            )
        )
    }
}


@Preview
@Composable
fun ScaffoldTopAppbarPreview() {
    ScaffoldTopAppbar(
        title = "Table",
        onNavigationIconClick = {

        }
    ) {

    }

}