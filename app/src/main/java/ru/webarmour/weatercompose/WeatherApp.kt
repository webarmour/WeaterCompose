package ru.webarmour.weatercompose

import android.app.Application
import ru.webarmour.weatercompose.di.ApplicationComponent
import ru.webarmour.weatercompose.di.DaggerApplicationComponent

class WeatherApp : Application() {

    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}