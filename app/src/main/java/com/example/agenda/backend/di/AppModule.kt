package com.example.agenda.backend.di

import androidx.room.Room
import com.example.agenda.backend.database.AppDatabase
import com.example.agenda.backend.repository.TasksRepository
import com.example.agenda.viewmodel.TaskFormViewModel
import com.example.agenda.viewmodel.HomeViewModel
import com.example.agenda.viewmodel.SearchViewModel
import com.example.agenda.viewmodel.TasksListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::TaskFormViewModel)
    viewModelOf(::TasksListViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::HomeViewModel)
}

val storageModule = module {
    singleOf(::TasksRepository)

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_agenda.db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<AppDatabase>().taskDao()
    }
}
