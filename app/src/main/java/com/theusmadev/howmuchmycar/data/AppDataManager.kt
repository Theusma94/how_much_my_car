package com.theusmadev.howmuchmycar.data

import com.theusmadev.howmuchmycar.data.remote.AppApiHelper


class AppDataManager constructor(var appApiHelper: AppApiHelper): DataManager {

    companion object {
        private val TAG: String = AppDataManager::class.java.simpleName
    }
}