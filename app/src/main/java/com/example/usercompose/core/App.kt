package com.example.usercompose.core

import android.app.Application
import com.example.usercompose.data.database.repository.UserRepository
import com.example.usercompose.data.network.NetworkManager

class App : Application() {
    lateinit var manager: NetworkManager
        private set
    lateinit var database: UserRepository
        private set

    override fun onCreate() {
        super.onCreate()


       manager = NetworkManager
        database = UserRepository(context = applicationContext)
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set

        fun getAppInstance(): App {
            return instance
        }
    }
}