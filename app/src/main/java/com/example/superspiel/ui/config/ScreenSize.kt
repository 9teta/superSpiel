package com.example.superspiel.ui.config

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ScreenSize(
    configuration: Configuration,
    density: Density
) {
    val widthInDp = configuration.screenWidthDp.dp
    val heightInDp = configuration.screenHeightDp.dp
    val widthInPx = with(density) { widthInDp.roundToPx() }
    val heightInPx = with(density) { heightInDp.roundToPx() }
    var leftHeight = 0.dp

    override fun toString(): String {
         return "dp width: $widthInDp, dp height: $heightInDp, px width: $widthInPx, px height: $heightInPx"
    }

    fun setScaffoldPaddingValues(innerPadding: PaddingValues?) {
        if (innerPadding != null) {
            leftHeight = heightInDp - innerPadding.calculateTopPadding() - innerPadding.calculateBottomPadding()
        }
    }

    fun returnLeftHeight(): Dp {
        return leftHeight
    }
}