package com.example.superspiel.apiProviders.openErApiCom

import com.example.superspiel.apiProviders.GenericApiProvider
import com.example.superspiel.network.NetworkService



class OpenErApiComProvider(networkService: NetworkService):
    GenericApiProvider(networkService, BASE_URL) {

    companion object {
        const val BASE_URL = "https://open.er-api.com/v6"
        const val SUFFIX_LATEST = "/latest"
        const val LATEST_DTO_FULL_URL = BASE_URL + SUFFIX_LATEST
    }



    suspend fun latest(): OpenErApiComDtosLatestDto {
        return networkService.get<OpenErApiComDtosLatestDto>(LATEST_DTO_FULL_URL)
    }


}