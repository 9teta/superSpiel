package com.example.superspiel.apiProviders.apiCoincapIo

import com.example.superspiel.apiProviders.GenericApiProvider
import com.example.superspiel.network.NetworkService

class ApiCoincapIoProvider(networkService: NetworkService):
    GenericApiProvider(networkService, BASE_URL) {

    companion object {
        const val BASE_URL = "https://api.coincap.io/v2"
        const val SUFFIX_ASSETS = "/assets"
        const val ASSETS_DTO_FULL_URL = BASE_URL + SUFFIX_ASSETS
    }



    suspend fun assets(): ApiCoincapIoDtosAssetsDto {
        return networkService.get<ApiCoincapIoDtosAssetsDto>(ASSETS_DTO_FULL_URL)
    }


}