package com.example.superspiel.composables.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.superspiel.apiProviders.ApiProvidersEndpoints
import com.example.superspiel.composables.liners.BaseCriptoFlowRow
import com.example.superspiel.composables.liners.BaseCurrencyFlowRow
import com.example.superspiel.composables.shimmers.LoadingShimmerEffect
import com.example.superspiel.global.AppViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrencyHorizontalPager(
    viewModel: AppViewModel? = null,
    lineHeight: Int,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val currencyState = viewModel?.openErApiComDtos_LatestDto?.collectAsStateWithLifecycle()
    val updateCurrencyState: () -> Unit = { viewModel?.updateValue(ApiProvidersEndpoints.OpenErApiCom_Latest) }

    HorizontalPager(
        state = pagerState,
        pageCount = 3,
        modifier = modifier
    ) { page ->
        LineWrapper(
            page = page,
            lineHeight = lineHeight,
        ) {
            if (currencyState?.value == null) {
                println("asking for a request")
                updateCurrencyState()
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(LoadingShimmerEffect()))
            } else {
                BaseCurrencyFlowRow(
                    rawState = currencyState?.value,
                    updateMyValue = updateCurrencyState,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CriptoHorizontalPager(
    viewModel: AppViewModel? = null,
    lineHeight: Int,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val criptoState = viewModel?.apiCoincapIoDtosAssetsDto?.collectAsStateWithLifecycle()
    val updateCriptoState: () -> Unit = { viewModel?.updateValue(ApiProvidersEndpoints.ApiCoincapIo_Assets) }

    HorizontalPager(
        state = pagerState,
        pageCount = 3,
        modifier = modifier
    ) { page ->
        LineWrapper(
            page = page,
            lineHeight = lineHeight,
        ) {
            if (criptoState?.value == null) {
                println("asking for a request")
                updateCriptoState()
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(LoadingShimmerEffect()))
            } else {
                BaseCriptoFlowRow(
                    rawState = criptoState?.value,
                    updateValue = updateCriptoState,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmptyHorizontalPager(
    lineHeight: Int,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0)
    HorizontalPager(
        state = pagerState,
        pageCount = 3,
        modifier = modifier
    ) { page ->
        LineWrapper(
            page = page,
            lineHeight = lineHeight,
        ) {
            Text(text = "Nothing here")
        }
    }
}