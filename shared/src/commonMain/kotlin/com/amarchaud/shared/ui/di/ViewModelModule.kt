package com.amarchaud.shared.ui.di

import com.amarchaud.shared.ui.screen.detail.UserDetailViewModel
import com.amarchaud.shared.ui.screen.mainList.MainListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainListViewModel(
            getRandomUsersWithRoomUseCase = get(),
        )
    }

    viewModel { (localId : Long) ->
        UserDetailViewModel(
            //stateHandle = get(), //todo KMM
            localId = localId,
            getUserFromCacheUseCase = get()
        )
    }
}