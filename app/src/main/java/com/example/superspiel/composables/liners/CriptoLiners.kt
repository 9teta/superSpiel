package com.example.superspiel.composables.liners

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoDtosAssetsDto
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoDtosAssetsDtoItem

private const val TAG = "Liners"
private const val NUMBER_ITEMS = 9


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BaseCriptoFlowRow(
    rawState: ApiCoincapIoDtosAssetsDto? = null,
    updateValue: () -> Unit,

    modifier: Modifier = Modifier
) {


    val list = with(rawState?.data) {
        if (this == null) {
            listOf<ApiCoincapIoDtosAssetsDtoItem>()
        } else {
            this.take(NUMBER_ITEMS)
        }
    }

    FlowRow(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        repeat( list.size ) {
            val dataItem = list[it]
            val symbol = dataItem.symbol
            val priceUsd = dataItem.priceUsd
            CriptoChip(symbol = symbol, priceUsd = priceUsd)
        }
    }

}

@Composable
fun CriptoChip(
    symbol: String,
    priceUsd: Double
) {

    val priceUsdString = String.format("%.2f", priceUsd)
    val text = "$symbol: S $priceUsdString"
    AssistChip(
        onClick = { },
        colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, labelColor = MaterialTheme.colorScheme.onSecondaryContainer),
        label = { Text(text = text) })
}

