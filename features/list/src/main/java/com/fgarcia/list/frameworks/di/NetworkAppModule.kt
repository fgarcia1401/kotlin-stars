package com.fgarcia.list.frameworks.di

import com.fgarcia.common.network.di.BaseUrl
import com.fgarcia.list.frameworks.network.GitRepositoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkAppModule {

    @Provides
    fun providerRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ): GitRepositoriesApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(GitRepositoriesApi::class.java)
    }

}
