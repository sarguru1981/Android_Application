package com.poc.data.network.di

import android.content.Context
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.network.repository.post.PostListRepositoryImpl
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.post.datasourceimpl.PostRemoteDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostCacheDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostLocalDataSourceImpl
import com.poc.data.network.repository.postdetail.PostDetailRepositoryImpl
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.data.network.repository.postdetail.datasource.PostDetailCacheDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailLocalDataSource
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailRemoteDataSourceImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailCacheDataSourceImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailLocalDataSourceImpl
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostDatabase
import com.poc.domain.repository.PostListRepository
import com.poc.domain.repository.PostDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostDatabase {
        return PostDatabase.getInstance(context)
    }

    @Provides
    fun provideDAO(postDatabase: PostDatabase): PostDAO {
        return postDatabase.getPostDAO()
    }

    // Injecting dispatcher makes testing easier as it can replace those dispatchers in
    // unit and instrumentation tests with a test dispatcher to make your tests more deterministic.
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    // Post List

    @Provides
    fun providePostLocalDataSource(postDAO: PostDAO): PostLocalDataSource {
        return PostLocalDataSourceImpl(postDAO)
    }

    @Provides
    fun providePostCacheDataSource(): PostCacheDataSource {
        return PostCacheDataSourceImpl()
    }

    @Provides
    fun provideGetPostRemoteDataSource(
        apiService: ApiService,
        retrofit: Retrofit
    ): PostRemoteDatasource {
        return PostRemoteDataSourceImpl(apiService, retrofit)
    }

    @Provides
    fun provideGetPostListRepository(
        postRemoteDatasource: PostRemoteDatasource,
        getPostLocalDataSource: PostLocalDataSource,
        getPostCacheDataSource: PostCacheDataSource,
        retrofit: Retrofit
    ): PostListRepository {
        return PostListRepositoryImpl(
            postRemoteDatasource, getPostLocalDataSource, getPostCacheDataSource, retrofit
        )
    }

    // Post Detail

    @Provides
    fun providePostDetailLocalDataSource(postDAO: PostDAO): PostDetailLocalDataSource {
        return PostDetailLocalDataSourceImpl(postDAO)
    }

    @Provides
    fun providePostDetailCacheDataSource(): PostDetailCacheDataSource {
        return PostDetailCacheDataSourceImpl()
    }

    @Provides
    fun provideGetPostDetailRemoteDataSource(
        apiService: ApiService,
        retrofit: Retrofit
    ): PostDetailRemoteDatasource {
        return PostDetailRemoteDataSourceImpl(apiService, retrofit)
    }

    @Provides
    fun provideGetPostDetailRepository(
        postDetailRemoteDatasource: PostDetailRemoteDatasource,
        retrofit: Retrofit
    ): PostDetailRepository {
        return PostDetailRepositoryImpl(
            postDetailRemoteDatasource, retrofit
        )
    }
}