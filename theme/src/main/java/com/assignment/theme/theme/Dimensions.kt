package com.assignment.theme.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Dimensions(
    // Font Sizes
    val titleSmall: TextUnit,
    val titleMedium: TextUnit,
    val titleLarge: TextUnit,
    val bodySmall: TextUnit,
    val bodyMedium: TextUnit,
    val bodyLarge: TextUnit,

    // Padding
    val paddingExtraSmall: Dp,
    val paddingSmall: Dp,
    val paddingMedium: Dp,
    val paddingLarge: Dp,
    val paddingExtraLarge: Dp,

    // Component Sizes
    val buttonHeight: Dp,
    val imageSizeExtraSmall: Dp,
    val imageSizeSmall: Dp,
    val imageSizeMedium: Dp,
    val imageSizeLarge: Dp,
    val cardMinWidth: Dp,
    val cardMinHeight: Dp,
    val boxMinHeight: Dp,

    val navIconSize: Dp,
    val navLabelFontSize: TextUnit,
    val navItemPadding: Dp,
)

val smallDimensions = Dimensions(
    titleSmall = 8.sp,
    titleMedium = 12.sp,
    titleLarge = 16.sp,
    bodySmall = 10.sp,
    bodyMedium = 12.sp,
    bodyLarge = 14.sp,

    paddingExtraSmall = 4.dp,
    paddingSmall = 8.dp,
    paddingMedium = 12.dp,
    paddingLarge = 16.dp,
    paddingExtraLarge = 20.dp,

    buttonHeight = 36.dp,
    imageSizeExtraSmall = 18.dp,
    imageSizeSmall = 40.dp,
    imageSizeMedium = 60.dp,
    imageSizeLarge = 80.dp,
    cardMinWidth = 130.dp,
    cardMinHeight = 130.dp,
    boxMinHeight = 60.dp,

    navIconSize = 20.dp,
    navLabelFontSize = 10.sp,
    navItemPadding = 2.dp,
)

val sw360Dimensions = Dimensions(
    titleSmall = 14.sp,
    titleMedium = 16.sp,
    titleLarge = 18.sp,
    bodySmall = 12.sp,
    bodyMedium = 14.sp,
    bodyLarge = 16.sp,

    paddingExtraSmall = 6.dp,
    paddingSmall = 10.dp,
    paddingMedium = 14.dp,
    paddingLarge = 20.dp,
    paddingExtraLarge = 36.dp,

    buttonHeight = 44.dp,
    imageSizeExtraSmall = 26.dp,
    imageSizeSmall = 48.dp,
    imageSizeMedium = 72.dp,
    imageSizeLarge = 96.dp,
    cardMinWidth = 180.dp,
    cardMinHeight = 180.dp,
    boxMinHeight = 70.dp,
    navIconSize = 24.dp,
    navLabelFontSize = 12.sp,
    navItemPadding = 4.dp,
)

val tabletDimensions = Dimensions(
    titleSmall = 16.sp,
    titleMedium = 20.sp,
    titleLarge = 24.sp,
    bodySmall = 14.sp,
    bodyMedium = 16.sp,
    bodyLarge = 18.sp,

    paddingExtraSmall = 8.dp,
    paddingSmall = 12.dp,
    paddingMedium = 20.dp,
    paddingLarge = 28.dp,
    paddingExtraLarge = 36.dp,

    buttonHeight = 56.dp,
    imageSizeExtraSmall = 30.dp,
    imageSizeSmall = 60.dp,
    imageSizeMedium = 100.dp,
    imageSizeLarge = 120.dp,
    cardMinWidth = 200.dp,
    cardMinHeight = 200.dp,
    boxMinHeight = 90.dp,

    navIconSize = 28.dp,
    navLabelFontSize = 14.sp,
    navItemPadding = 6.dp,
)