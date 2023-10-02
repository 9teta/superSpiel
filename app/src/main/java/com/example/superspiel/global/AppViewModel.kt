package com.example.superspiel.global

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superspiel.apiProviders.ApiProvidersEndpoints
import com.example.superspiel.apiProviders.apiCoincapIo.ApiCoincapIoDtosAssetsDto
import com.example.superspiel.apiProviders.openErApiCom.OpenErApiComDtosLatestDto
import com.example.superspiel.network.ApiToDtoBinder
import com.example.superspiel.network.NetworkDataStatus
import com.example.superspiel.ui.config.ScreenSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicReference

const val TAG = "AppViewModel"

class AppViewModel(): ViewModel() {

    var screenSize: ScreenSize? = null
        set(value) {
            println(value)
            field = value
        }
    private val apiToDtoBinder: ApiToDtoBinder = ApiToDtoBinder

    // MutableStateFlow<T> -> StateFlow<T> -[collect]-> State<T> -> <T>
    private val _openErApiComDtos_LatestDto = MutableStateFlow<OpenErApiComDtosLatestDto?>(null)
    val openErApiComDtos_LatestDto = _openErApiComDtos_LatestDto.asStateFlow()
        get() {
            if ( field == null ) {
                println("own get fun asking for a request")
                updateValue(ApiProvidersEndpoints.OpenErApiCom_Latest)
            }
            return field
        }
    private var openErApiComDtos_LatestDtoStatus = AtomicReference(NetworkDataStatus.NULL)
    //================================================//
    private val _apiCoincapIoDtosAssetsDto = MutableStateFlow<ApiCoincapIoDtosAssetsDto?>(null)
    val apiCoincapIoDtosAssetsDto = _apiCoincapIoDtosAssetsDto.asStateFlow()
        get() {
            if ( field == null ) {
                println("own get fun asking for a request")
                updateValue(ApiProvidersEndpoints.ApiCoincapIo_Assets)
            }
            return field
        }
    private var apiCoincapIoDtosAssetsDtoStatus = AtomicReference(NetworkDataStatus.NULL)

    fun updateValue(endpoint: ApiProvidersEndpoints) {
        when(endpoint) {
            ApiProvidersEndpoints.OpenErApiCom_Latest -> {
                if ( openErApiComDtos_LatestDtoStatus.get() == NetworkDataStatus.LOADING ) {
                    println("loading...")
                } else if (openErApiComDtos_LatestDtoStatus.get() == NetworkDataStatus.DONE) {
                    println("already has been loaded")
                } else {
                    openErApiComDtos_LatestDtoStatus.set(NetworkDataStatus.LOADING)
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            _openErApiComDtos_LatestDto.update { apiToDtoBinder.openErApiCom_latest() }
                            openErApiComDtos_LatestDtoStatus.set(NetworkDataStatus.DONE)
                        } catch (e: Exception) {
                            Log.e(TAG, "Can't update ${endpoint.name}", e)
                            openErApiComDtos_LatestDtoStatus.set(NetworkDataStatus.ERROR)
                        }
                    }
                }
            }
            ApiProvidersEndpoints.ApiCoincapIo_Assets -> {
                if (apiCoincapIoDtosAssetsDtoStatus.get() == NetworkDataStatus.LOADING) {
                    println("loading...")
                } else if (apiCoincapIoDtosAssetsDtoStatus.get() == NetworkDataStatus.DONE) {
                    println("already has been loaded")
                } else {
                    apiCoincapIoDtosAssetsDtoStatus.set(NetworkDataStatus.LOADING)
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            _apiCoincapIoDtosAssetsDto.update { apiToDtoBinder.apiCoincapIo_assets() }
                            apiCoincapIoDtosAssetsDtoStatus.set(NetworkDataStatus.DONE)
                        } catch (e: Exception) {
                            Log.e(TAG, "Can't update ${endpoint.name}", e)
                            apiCoincapIoDtosAssetsDtoStatus.set(NetworkDataStatus.ERROR)
                        }
                    }
                }
            }
        }
    }



}