package com.assignment.hometab.orders

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.assignment.core.bases.BaseScreen
import com.assignment.core.bases.BaseViewState

@Composable
internal fun OrdersScreen(
    modifier: Modifier = Modifier,
) {
    BaseScreen(
        baseViewState = BaseViewState.Idle,
        content = {
            Text(
                text = "OrdersScreen",
                modifier = modifier
            )
        },
    )

}



