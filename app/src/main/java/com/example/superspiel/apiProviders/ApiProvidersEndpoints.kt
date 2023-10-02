package com.example.superspiel.apiProviders

import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoDtosAssetsDto
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoProvider
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComProvider
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComDtosLatestDto
import kotlin.reflect.KClass

enum class ApiProvidersEndpoints(
    val dataClass: KClass<*>,
    val apiProvider: KClass<*>,
    val fullUrl: String,
) {

    OpenErApiCom_Latest(
        OpenErApiComDtosLatestDto::class,
        OpenErApiComProvider::class,
        OpenErApiComProvider.LATEST_DTO_FULL_URL
    ),

    ApiCoincapIo_Assets(
        ApiCoincapIoDtosAssetsDto::class,
        ApiCoincapIoProvider::class,
        ApiCoincapIoProvider.ASSETS_DTO_FULL_URL
    ),

}