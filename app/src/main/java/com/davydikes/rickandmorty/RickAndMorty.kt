package com.davydikes.rickandmorty

import android.app.Application
import com.davydikes.rickandmorty.api.ApiRickAndMorty
import com.davydikes.rickandmorty.database.DatabaseConstructor
import com.davydikes.rickandmorty.database.RickAndMortyDatabase
import com.davydikes.rickandmorty.repositories.RepositoryApi
import com.davydikes.rickandmorty.repositories.RepositoryCharacters
import com.davydikes.rickandmorty.screen.main.ViewModelMain
import com.davydikes.rickandmorty.support.NetworkConnection
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class RickAndMorty : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMorty)
            modules(
                listOf(
                    storageModule,
                    apiModule,
                    viewModels,
                    repositoryModule,
                    connection
                )
            )
        }
    }

    private val viewModels = module {
        viewModel { ViewModelMain(get(), get(), get()) }
    }

    private val repositoryModule = module {
        factory { RepositoryCharacters(get()) }
        factory { RepositoryApi(get(), get()) }
    }

    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }
        factory { get<RickAndMortyDatabase>().charactersDao() }
    }

    private val apiModule = module {
        factory { ApiRickAndMorty.get() }
    }

    private val connection = module {
        single { NetworkConnection(get()) }
    }
}