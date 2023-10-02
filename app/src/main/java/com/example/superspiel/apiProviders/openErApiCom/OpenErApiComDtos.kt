package com.example.superspiel.apiProviders.openErApiCom

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date


@Serializable
data class OpenErApiComDtosLatestDto(
    val result: String,
    @SerialName("time_last_update_unix") val timeLastUpdateUnix: Long,
    @SerialName("base_code") val baseCode: String,
    val rates: Map<String, Double>,
    @Transient @Contextual val timeLastUpdateDate: Date = Date(timeLastUpdateUnix),
)

@Serializable
data class OpenErApiComDtosFirstDto(
    val ddd: String,
)