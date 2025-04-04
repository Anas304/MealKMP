package org.example.project.meal.di

import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.core.context.startKoin

fun initKoin(config: KoinAppDeclaration = {}){
    startKoin {
        config.invoke(this)
        modules(sharedModule, platformModule)
    }
}