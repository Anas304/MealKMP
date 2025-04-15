package org.example.project.meal.di

import org.example.core.data.HttpClientFactory
import org.example.project.meal.data.network.KtorRemoteMealDataSource
import org.example.project.meal.data.network.RemoteMealDataSource
import org.example.project.meal.data.repository.DefaultMealRepository
import org.example.project.meal.domain.MealRepository
import org.example.project.meal.presentation.SelectedMealViewModel
import org.example.project.meal.presentation.meal_detail.MealDetailsViewModel
import org.example.project.meal.presentation.meal_list.MealListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single { HttpClientFactory.createHttpClient(get()) }
    singleOf(::KtorRemoteMealDataSource) bind RemoteMealDataSource::class
    singleOf(::DefaultMealRepository) bind MealRepository::class

    viewModelOf(::MealListViewModel)//will not put bind here as this does not implement abstraction
    viewModelOf(::SelectedMealViewModel)
    viewModelOf(::MealDetailsViewModel)
}