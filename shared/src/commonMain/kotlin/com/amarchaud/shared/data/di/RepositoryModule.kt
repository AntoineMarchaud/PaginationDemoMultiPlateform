package com.amarchaud.shared.data.di

import com.amarchaud.shared.data.repository.PaginationDemoRepositoryImpl
import com.amarchaud.shared.domain.repository.PaginationDemoRepository
import org.koin.dsl.module

val repoModule = module {
    single<PaginationDemoRepository> {
        PaginationDemoRepositoryImpl(
            paginationDemoDao = get(),
            paginationDemoApi = get()
        )
    }
}