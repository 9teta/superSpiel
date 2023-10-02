package com.example.superspiel.apiProviders

import com.example.superspiel.network.NetworkService

open class GenericApiProvider(
    protected val networkService: NetworkService,
    protected val _baseUrl: String
) {

    open fun getBaseUrl(): String {
        return _baseUrl
    }

}