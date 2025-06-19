package com.assignment.hometab.tables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.assignment.core.bases.BaseScreen
import com.assignment.core.bases.BaseViewState
import com.assignment.hometab.R
import com.assignment.theme.component.ScaffoldTopAppbar

@Composable
internal fun TablesScreen(
    modifier: Modifier = Modifier,
) {
    BaseScreen(
        baseViewState = BaseViewState.Idle,
        content = {
            TableListScreen(
                modifier = modifier,
            )
        },
    )

}

@Composable
fun TableListScreen(
    modifier: Modifier = Modifier,
) {

    ScaffoldTopAppbar(
        title = stringResource(id = R.string.title_table)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "Hello222!",
                modifier = modifier
            )
        }
    }

}


