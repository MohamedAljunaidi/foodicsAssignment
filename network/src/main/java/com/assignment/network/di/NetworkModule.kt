package com.assignment.network.di
import com.assignment.network.BuildConfig
import com.assignment.network.services.ApiManager
import io.ktor.client.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.assignment.network.di")
class NetworkModule {

    @Single
    fun provideLogging() =
        LoggingInterceptor()

    @Single
    fun provideLoggingInterceptor(loggingInterceptor: LoggingInterceptor) =
        HttpLoggingInterceptor(loggingInterceptor).apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Single
    fun provideKtorClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): HttpClient =
        NetworkFactory.getKtorClient(httpLoggingInterceptor)

    @Single
    fun provideApiManager(client: HttpClient): ApiManager =
        ApiManager(client)
}
