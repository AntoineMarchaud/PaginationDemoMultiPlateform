package com.amarchaud.shared.domain.di

import com.amarchaud.shared.domain.repository.PaginationDemoRepository
import com.amarchaud.shared.domain.usecase.GetRandomUsersWithRoomUseCase
import com.amarchaud.shared.domain.usecase.GetUserFromCacheUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserFromCacheUseCase> {
        GetUserFromCacheUseCase(
            repository = get<PaginationDemoRepository>()
        )
    }

    factory<GetRandomUsersWithRoomUseCase> {
        GetRandomUsersWithRoomUseCase(
            repository = get<PaginationDemoRepository>()
        )
    }
}