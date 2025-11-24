package org.example.project

import android.app.Application
import org.example.project.meal.di.initKoin
import org.koin.android.ext.koin.androidContext

class MealApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@MealApplication)
        }
    }
}