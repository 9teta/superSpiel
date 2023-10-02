package com.example.superspiel.network

import com.example.superspiel.apiProviders.ApiProvidersEndpoints
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoDtosAssetsDto
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoProvider
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComProvider
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComDtosLatestDto

object ApiToDtoBinder {
    private val networkService: NetworkService = NetworkService
    private val openErApiComProvider: OpenErApiComProvider = OpenErApiComProvider(networkService)
    private val apiCoincapIoProvider: ApiCoincapIoProvider = ApiCoincapIoProvider(networkService)

    suspend fun openErApiCom_latest(): OpenErApiComDtosLatestDto {
        return openErApiComProvider.latest()
    }
    suspend fun apiCoincapIo_assets(): ApiCoincapIoDtosAssetsDto {
        return apiCoincapIoProvider.assets()
    }

    suspend fun obtain(endpoint: ApiProvidersEndpoints) {
        when(endpoint) {
            ApiProvidersEndpoints.OpenErApiCom_Latest -> openErApiComProvider.latest()
            ApiProvidersEndpoints.ApiCoincapIo_Assets -> apiCoincapIoProvider.assets()
        }
    }

}