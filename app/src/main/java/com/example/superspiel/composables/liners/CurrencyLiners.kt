package com.example.superspiel.composables.liners

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComDtosLatestDto
import com.example.superspiel.global.CurrenciesPriority

private const val TAG = "Liners"
private const val NUMBER_OF_CHIPS = 9

@Composable
fun BaseCurrencyFlowRow(
    rawState: OpenErApiComDtosLatestDto? = null,
    updateMyValue: () -> Unit,

    modifier: Modifier = Modifier
) {

    val currencyCode = rawState?.baseCode ?: "?";
    Log.i(TAG, "Obtain null raw state: ${rawState == null}")
    val list: List<CurrencyNameRate> = rawState.let {
        if (it == null) {
            updateMyValue()
            listOf<CurrencyNameRate>()
        } else {
            it.rates.map { CurrencyNameRate(it.key, it.value) }
        }
    }
    Log.d(TAG, "looking at list of size ${list.size}")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()

    ) {
        BaseCurrencyToken(currencyCode)
        ChipsContainer(
            baseCurrency = currencyCode,
            currencyPriority = CurrenciesPriority().priority,
            numberOfChips = NUMBER_OF_CHIPS,
            list = list)
    }

}

@Composable
fun BaseCurrencyToken(text: String = "") {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer),
        modifier = Modifier
            .height(100.dp)
            .aspectRatio(1f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
                )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsContainer(
    numberOfChips: Int = 0,
    list: List<CurrencyNameRate> = listOf(),
    baseCurrency: String,
    currencyPriority: List<String>
) {
    val sortedList = prioritizeCurrencies(list, currencyPriority)
    FlowRow(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        repeat( numberOfChips ) {
            val item = sortedList[it]
            if (item.symbol != baseCurrency) {
                CurrencyChip(symbol = item.symbol, rate = item.rate)
            }
        }
    }
}

@Composable
fun CurrencyChip(symbol: String = "?", rate: Double = 0.0) {
    val fullText = "$symbol: ${String.format("%.2f", rate)}"
    AssistChip(
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            labelColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
        onClick = {  },
        label = { Text(text = fullText) })
}

data class CurrencyNameRate(val symbol: String, val rate: Double)

private fun prioritizeCurrencies(
    list: List<CurrencyNameRate> = listOf(),
    currencyPriority: List<String>
): MutableList<CurrencyNameRate> {

    val sortedList: MutableList<CurrencyNameRate> = mutableListOf()
    for ( currencyCode in currencyPriority ) {
        val found = list.find { currencyCode == it.symbol }
        if (found != null) sortedList.add(found)
    }
    return sortedList
}