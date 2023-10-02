package com.example.superspiel.composables.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.superspiel.apiProviders.ApiProvidersEndpoints
import com.example.superspiel.composables.liners.BaseCurrencyFlowRow
import com.example.superspiel.composables.shimmers.LoadingShimmerEffect
import com.example.superspiel.global.AppViewModel
import com.example.superspiel.ui.config.ScreenSize

const val LINE_HEIGHT = 200

@Preview(showBackground = true)
@Composable
fun MainPager(
    viewModel: AppViewModel? = null,
    innerPadding: PaddingValues? = null,
    modifier: Modifier = Modifier,
) {

    val screenSize = ScreenSize(LocalConfiguration.current, LocalDensity.current)
    val leftHeight = screenSize.run {
        setScaffoldPaddingValues(innerPadding)
        screenSize.returnLeftHeight()
    }
    val numberOfLines = (leftHeight / LINE_HEIGHT.dp).toInt()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxSize()
    ) {
        repeat(numberOfLines) {
            if (it == 0) {
                CurrencyHorizontalPager(
                    viewModel = viewModel,
                    lineHeight = LINE_HEIGHT,
                    modifier = Modifier)
            } else if (it == 1) {
                CriptoHorizontalPager(
                    viewModel = viewModel,
                    lineHeight = LINE_HEIGHT,
                    modifier = Modifier)
            } else {
                EmptyHorizontalPager(lineHeight = LINE_HEIGHT)
            }
        }
    }



}

@Composable
fun LineWrapper(
    page: Int,
    lineHeight: Int,
    liner: @Composable () -> Unit
) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .height(lineHeight.dp)

    ) {
        liner()
    }
}