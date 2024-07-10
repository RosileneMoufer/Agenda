package com.example.agenda.backend.di

import com.example.agenda.viewmodel.FormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { FormViewModel() }
}
