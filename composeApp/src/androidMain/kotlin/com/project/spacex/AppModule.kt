package com.project.spacex

import com.project.spacex.cache.AndroidDatabaseDriverFactory
import com.project.spacex.network.SpaceXApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SpaceXApi> { SpaceXApi() }
    single<SpaceXSDK> {
        SpaceXSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            ), api = get()
        )
    }
    viewModel { RocketLaunchViewModel(sdk = get()) }
}