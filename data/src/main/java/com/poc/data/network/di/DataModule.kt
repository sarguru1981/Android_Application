package com.poc.data.network.di

import android.content.Context
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.PostDetailRemoteDataSourceImpl
import com.poc.common.Constant
import com.poc.data.ApiService
import com.poc.data.network.repository.post.GetPagerPostRepoImpl
import com.poc.data.network.repository.post.GetPostsRepositoryImpl
import com.poc.data.network.repository.post.datasource.PostCacheDataSource
import com.poc.data.network.repository.post.datasource.PostLocalDataSource
import com.poc.data.network.repository.post.datasource.PostRemoteDatasource
import com.poc.data.network.repository.post.datasourceimpl.PostCacheDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostLocalDataSourceImpl
import com.poc.data.network.repository.post.datasourceimpl.PostRemoteDataSourceImpl
import com.poc.data.network.repository.postdetail.GetPostDetailRepositoryImpl
import com.poc.data.network.repository.postdetail.datasource.PostDetailCacheDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailLocalDataSource
import com.poc.data.network.repository.postdetail.datasource.PostDetailRemoteDatasource
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailCacheDataSourceImpl
import com.poc.data.network.repository.postdetail.datasourceimpl.PostDetailLocalDataSourceImpl
import com.poc.data.room.post.PostDAO
import com.poc.data.room.post.PostDatabase
import com.poc.domain.repository.GetPagerPostRepo
import com.poc.domain.repository.GetPostDetailRepository
import com.poc.domain.repository.GetPostsRepository
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
    fun provideGetPostsRepository(
        apiService: ApiService,
        ioDispatcher: CoroutineDispatcher
    ): GetPostsRepository {
        return GetPostsRepositoryImpl(apiService = apiService, ioDispatcher = ioDispatcher)
    }

    /*@Provides
    fun provideGetUsersRepository(
        apiService: ApiService,
        ioDispatcher: CoroutineDispatcher
    ): GetUsersRepository {
        return GetUsersRepositoryImpl(apiService = apiService, ioDispatcher = ioDispatcher)
    }*/

   /* @Provides
    fun provideGetPostDetailsRepo(
        apiService: ApiService,
        ioDispatcher: CoroutineDispatcher
    ): GetPostDetailRepository {
        return GetPostDetailRepositoryImpl(apiService, ioDispatcher = ioDispatcher)
    }*/

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostDatabase {
        return PostDatabase.getInstance(context)
    }

    @Provides
    fun provideDAO(postDatabase: PostDatabase): PostDAO {
        return postDatabase.getPostDAO()
    }

    /*@Provides
    fun provideGetPagerRepo(apiService: ApiService): GetPagerPostRepo {
        return GetPagerPostRepoImpl(apiService)
    }*/

    // Injecting dispatcher makes testing easier as it can replace those dispatchers in
    // unit and instrumentation tests with a test dispatcher to make your tests more deterministic.
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    fun providePostRemoteDataSource(apiService: ApiService): PostRemoteDatasource {
        return PostRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun providePostLocalDataSource(postDAO: PostDAO): PostLocalDataSource {
        return PostLocalDataSourceImpl(postDAO)
    }
    @Provides
    fun providePostCacheDataSource(): PostCacheDataSource {
        return PostCacheDataSourceImpl()
    }

    @Provides
    fun provideGetPostListRepository(
        postRemoteDatasource: PostRemoteDatasource,
        postLocalDataSource: PostLocalDataSource,
        postCacheDataSource: PostCacheDataSource
    ): GetPagerPostRepo {

        return GetPagerPostRepoImpl(
            postRemoteDatasource,
            postLocalDataSource,
            postCacheDataSource
        )
    }

    @Provides
    fun providePostDetailRemoteDataSource(
        apiService: ApiService,
        ioDispatcher: CoroutineDispatcher
    ): PostDetailRemoteDatasource {
        return PostDetailRemoteDataSourceImpl(apiService, ioDispatcher)
    }

    @Provides
    fun providePostDetailLocalDataSource(postDAO: PostDAO): PostDetailLocalDataSource {
        return PostDetailLocalDataSourceImpl(postDAO)
    }
    @Provides
    fun providePostDetailCacheDataSource(): PostDetailCacheDataSource {
        return PostDetailCacheDataSourceImpl()
    }

    @Provides
    fun provideGetPostDetailRepository(
        postDetailRemoteDatasource: PostDetailRemoteDatasource,
        postDetailLocalDataSource: PostDetailLocalDataSource,
        postDetailCacheDataSource: PostDetailCacheDataSource
    ): GetPostDetailRepository {

        return GetPostDetailRepositoryImpl(
            postDetailRemoteDatasource,
            postDetailLocalDataSource,
            postDetailCacheDataSource
        )
    }
}