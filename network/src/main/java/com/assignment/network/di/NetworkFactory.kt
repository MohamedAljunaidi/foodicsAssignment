package com.assignment.network.di

import com.assignment.network.network.common.NetworkConstants
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkFactory {

    fun getKtorClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): HttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return HttpClient(OkHttp) {
            engine {
                preconfigured = okHttpClient
            }
            install(ContentNegotiation) {
                gson()
            }
        }
    }
}

