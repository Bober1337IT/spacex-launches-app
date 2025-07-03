package com.project.spacex

import com.project.spacex.cache.Database
import com.project.spacex.cache.DatabaseDriverFactory
import com.project.spacex.entity.RocketLaunch
import com.project.spacex.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory, val api: SpaceXApi) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearAndCreateLaunches(it)
            }
        }
    }
    @Throws(Exception::class)
    suspend fun getLaunchById(flightNumber: Int): RocketLaunch? {
        return database.getLaunchById(flightNumber.toLong())
    }
}