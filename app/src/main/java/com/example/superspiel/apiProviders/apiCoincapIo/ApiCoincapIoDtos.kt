package com.example.superspiel.apiProviders.apiCoincapIo

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ApiCoincapIoDtosAssetsDto(
    val data: List<ApiCoincapIoDtosAssetsDtoItem> ,
    val timestamp: Long
)

@Serializable
data class ApiCoincapIoDtosEmptyDto(
    val ddd: String,
)

@Serializable
data class ApiCoincapIoDtosAssetsDtoItem(
    val id: String,
    val symbol: String,
    val name: String,
    val priceUsd: Double,
)
