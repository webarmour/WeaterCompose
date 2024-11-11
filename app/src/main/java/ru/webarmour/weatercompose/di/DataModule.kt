package ru.webarmour.weatercompose.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.webarmour.weatercompose.data.local.db.FavouriteCitiesDao
import ru.webarmour.weatercompose.data.local.db.FavouriteDatabase
import ru.webarmour.weatercompose.data.network.api.ApiFactory
import ru.webarmour.weatercompose.data.network.api.ApiService
import ru.webarmour.weatercompose.data.repository.FavouriteRepositoryImpl
import ru.webarmour.weatercompose.data.repository.SearchRepositoryImpl
import ru.webarmour.weatercompose.data.repository.WeatherRepositoryImpl
import ru.webarmour.weatercompose.domain.repository.FavouriteRepository
import ru.webarmour.weatercompose.domain.repository.SearchRepository
import ru.webarmour.weatercompose.domain.repository.WeatherRepository


@Module
interface DataModule {


    @ApplicationScope
    @Binds
    fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @ApplicationScope
    @Binds
    fun bindSearchRepository(searchRepo: SearchRepositoryImpl): SearchRepository

    @ApplicationScope
    @Binds
    fun bindFavouritesRepository(favouriteRepository: FavouriteRepositoryImpl): FavouriteRepository


    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService
        @ApplicationScope

        @Provides
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase {
            return FavouriteDatabase.getInstance(context)
        }

        @ApplicationScope
        @Provides
        fun provideFavouriteCitiesDao(database: FavouriteDatabase):FavouriteCitiesDao {
            return database.favouriteCitiesDao()
        }
    }


}



