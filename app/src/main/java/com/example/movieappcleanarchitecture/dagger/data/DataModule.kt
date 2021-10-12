package com.example.movieappcleanarchitecture.dagger.data

import android.content.Context
import androidx.room.Room
import com.example.movieappcleanarchitecture.dagger.DI
import com.example.movieappcleanarchitecture.data.api.Api
import com.example.movieappcleanarchitecture.data.db.MoviesDatabase
import com.example.movieappcleanarchitecture.data.db.RoomFavoritesMovieCache
import com.example.movieappcleanarchitecture.data.mappers.DetailsDataMovieEntityMapper
import com.example.movieappcleanarchitecture.data.mappers.MovieDataEntityMapper
import com.example.movieappcleanarchitecture.data.mappers.MovieEntityDataMapper
import com.example.movieappcleanarchitecture.data.repository.CachedMoviesDataStore
import com.example.movieappcleanarchitecture.data.repository.MemoryMoviesCache
import com.example.movieappcleanarchitecture.data.repository.MoviesRepositoryImpl
import com.example.movieappcleanarchitecture.data.repository.RemoteMoviesDataStore
import com.example.movieappcleanarchitecture.domain.MoviesCache
import com.example.movieappcleanarchitecture.domain.MoviesDataStore
import com.example.movieappcleanarchitecture.domain.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies_db").build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: Api,
                               @Named(DI.inMemoryCache) cache: MoviesCache,
                               movieDataMapper: MovieDataEntityMapper,
                               detailedDataMapper: DetailsDataMovieEntityMapper
    ): MoviesRepository {

        return MoviesRepositoryImpl(api, cache, movieDataMapper, detailedDataMapper)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryMoviesCache(): MoviesCache {
        return MemoryMoviesCache()
    }

    @Singleton
    @Provides
    @Named(DI.favoritesCache)
    fun provideFavoriteMoviesCache(moviesDatabase: MoviesDatabase,
                                   entityDataMapper: MovieEntityDataMapper,
                                   dataEntityMapper: MovieDataEntityMapper): MoviesCache {
        return RoomFavoritesMovieCache(moviesDatabase, entityDataMapper, dataEntityMapper)
    }

    @Singleton
    @Provides
    @Named(DI.remoteDataStore)
    fun provideRemoteMovieDataStore(api: Api, movieDataMapper: MovieDataEntityMapper, detailedDataMapper: DetailsDataMovieEntityMapper): MoviesDataStore {
        return RemoteMoviesDataStore(api, movieDataMapper, detailedDataMapper)
    }

    @Singleton
    @Provides
    @Named(DI.cachedDataStore)
    fun provideCachedMoviesDataStore(moviesCache: MoviesCache): MoviesDataStore {
        return CachedMoviesDataStore(moviesCache)
    }

}