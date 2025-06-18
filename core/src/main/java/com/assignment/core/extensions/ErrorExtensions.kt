package com.assignment.core.extensions

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.assignment.core.R
import com.assignment.core.model.ResultException
import com.assignment.core.model.ServerExceptionType

@Composable
fun ResultException.HandleErrorState(
    dialogUnauthorizedCallBack: (() -> Unit)? = null,
    dialogDismissCallBack: (() -> Unit)? = null,
    dialogTryAgainCallBack: (() -> Unit)? = null,
) {
    when (this.errorModel?.errorType ?: ServerExceptionType.UnExpectedError) {
        ServerExceptionType.Unauthorized, ServerExceptionType.Forbidden -> {
            ShowUnauthorizedErrorDialog {
                dialogUnauthorizedCallBack?.invoke()
            }
        }

        ServerExceptionType.ServerError, ServerExceptionType.UnExpectedError -> {
            ShowGeneralErrorDialog(
                onDismiss = {
                    dialogDismissCallBack?.invoke()
                }
            )
        }

        ServerExceptionType.ClientError -> {
            ShowGeneralErrorDialog(
                onDismiss = {
                    dialogDismissCallBack?.invoke()
                },
                message = this.errorModel?.errorDescription
            )
        }

        ServerExceptionType.NoInternetConnection -> {
            ShowNetworkConnectionErrorDialog {
                dialogTryAgainCallBack?.invoke()
            }
        }
    }
}

@Composable
fun ShowGeneralErrorDialog(
    onDismiss: (() -> Unit)? = null,
    onTryAgain: (() -> Unit)? = null,
    message: String? = stringResource(id = R.string.error_general_message)
) {

    val shouldShowDialog = remember { mutableStateOf(true) }

    if (shouldShowDialog.value) {

        AlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                shouldShowDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.error_general_title))
            },
            text = {
                Text(text = message ?: "")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onTryAgain?.invoke()
                        onDismiss?.invoke()
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = if (onTryAgain != null) R.string.dialog_try_again else R.string.dialog_ok))
                }
            },
            dismissButton = {
                if (onTryAgain != null) {
                    Button(
                        onClick = {
                            onDismiss?.invoke()
                            shouldShowDialog.value = false

                        }
                    ) {
                        Text(text = stringResource(id = R.string.dialog_ok))
                    }
                }
            }
        )
    }

}

@Composable
fun ShowUnauthorizedErrorDialog(
    unauthorizedCallBack: (() -> Unit)? = null
) {

    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                unauthorizedCallBack?.invoke()
                shouldShowDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.error_unauthorized_title))
            },
            text = {
                Text(text = stringResource(id = R.string.error_unauthorized_message))
            },
            confirmButton = {
                Button(
                    onClick = {
                        unauthorizedCallBack?.invoke()
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.dialog_continue))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

}

@Composable
fun ShowNetworkConnectionErrorDialog(
    onDismiss: (() -> Unit)? = null
) {
    val shouldShowDialog = remember { mutableStateOf(true) }
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                shouldShowDialog.value = false

            },
            title = {
                Text(text = stringResource(id = R.string.error_network_connection_title))
            },
            text = {
                Text(text = stringResource(id = R.string.error_network_connection_message))
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss?.invoke()
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.dialog_try_again))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

}
