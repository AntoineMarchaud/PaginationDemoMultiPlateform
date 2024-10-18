package com.amarchaud.shared.data.di

import com.amarchaud.shared.data.api.PaginationDemoApi
import com.amarchaud.shared.data.api.PaginationDemoApiKtorImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

actual val networkModule: Module  = module {

    // Provide the Ktor HttpClient
    single<HttpClient> {
        HttpClient(Darwin) {
            install(DefaultRequest) {
                url("https://randomuser.me/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(Json)
            }
        }
    }

    // Provide the API implementation
    single<PaginationDemoApi> {
        PaginationDemoApiKtorImpl(get())
    }
}