package com.fuadhev.goldenpaytask.people.di

import com.fuadhev.goldenpaytask.common.Constant.BASE_URL
import com.fuadhev.goldenpaytask.people.data.api.WebApiService
import com.fuadhev.goldenpaytask.people.data.datasource.PeopleRemoteDataSource
import com.fuadhev.goldenpaytask.people.data.datasource.PeopleRemoteDataSourceImpl
import com.fuadhev.goldenpaytask.people.data.repository.PeopleRepository
import com.fuadhev.goldenpaytask.people.data.repository.PeopleRepositoryImpl
import com.fuadhev.goldenpaytask.people.domain.usecase.GetPeopleItemListUseCase
import com.fuadhev.goldenpaytask.people.domain.usecase.GetPeopleUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Singleton
    @Provides
    fun provideIoContext(): CoroutineDispatcher = Dispatchers.IO

}


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {


    @Singleton
    @Binds
    abstract fun providePeopleRemoteDataSource(dataSource: PeopleRemoteDataSourceImpl): PeopleRemoteDataSource


}


@Module
@InstallIn(SingletonComponent::class)
abstract class PeopleRepositoryModule {


    @Singleton
    @Binds
    abstract fun providePeopleRepository(remoteRepository: PeopleRepositoryImpl): PeopleRepository


}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Singleton
    @Provides
    fun provideGetPeopleUseCase(coroutineContext: CoroutineDispatcher,repository: PeopleRepository):GetPeopleUseCase{
        return  GetPeopleUseCase(coroutineContext,repository)
    }

    @Singleton
    @Provides
    fun provideGetPeopleItemListUseCase():GetPeopleItemListUseCase{
        return  GetPeopleItemListUseCase()
    }


}


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WebApiService =
        retrofit.create(WebApiService::class.java)

}