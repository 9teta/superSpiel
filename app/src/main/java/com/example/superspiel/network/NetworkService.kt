package com.example.superspiel.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkService {

    val webClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json( Json { ignoreUnknownKeys = true } )
        }
    }

    fun closeWebClient() {
        webClient.close()
    }

    suspend inline fun <reified T> get(url: String): T {
        println("doing a request")
        val response = webClient.get(url)
        if (response.status.value !in 200..299 ) {
            println("bad response with code ${response.status.value}")
        }
        println(response.bodyAsText())
        return response.body<T>()
    }




}
