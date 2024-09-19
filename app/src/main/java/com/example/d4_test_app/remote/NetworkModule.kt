package com.example.d4_test_app.remote

import com.example.d4_test_app.remote.BASE_URL.API_BASE_URL
import com.example.d4_test_app.remote.repo.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {




    /**
     * OkHttpClient instance.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()


    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): MovieApi =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieApi::class.java)




}