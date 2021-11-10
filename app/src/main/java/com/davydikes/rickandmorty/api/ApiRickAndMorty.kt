package com.davydikes.rickandmorty.api;
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRickAndMorty {
    @GET("character/")
    suspend fun getNextPageCharacter(@Query("page")id: Int):Response<PageCharacter>
    @GET("character/")
    suspend fun getFirstPageCharacter():Response<PageCharacter>

    companion object {
        private const val API_URL = "https://rickandmortyapi.com/api/"

        fun get(): ApiRickAndMorty = Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
        }.build()
            )
            .build().create(ApiRickAndMorty::class.java)
    }
}
