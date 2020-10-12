package com.v_kii_rom.data.remote.helpers

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.v_kii_rom.data.remote.services.HeroesService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory {

    companion object{
        private const val baseUrl = "https://api.opendota.com/api/"
        private fun getOkHttpInstance(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        @ExperimentalSerializationApi
        private fun getRetrofitClient (): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpInstance())
                .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        @ExperimentalSerializationApi
        fun getHeroesService(): HeroesService = RetrofitFactory.getRetrofitClient().create(HeroesService::class.java)

    }
}