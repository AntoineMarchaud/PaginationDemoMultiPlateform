package com.amarchaud.shared.ui.screen.mainList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.amarchaud.shared.domain.models.UserModel
import com.amarchaud.shared.domain.usecase.GetRandomUsersWithRoomUseCase
import com.amarchaud.shared.ui.screen.mainList.models.UserGenericUiModel
import com.amarchaud.shared.ui.screen.mappers.toGenericUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainListViewModel(
    getRandomUsersWithRoomUseCase: GetRandomUsersWithRoomUseCase
) : ViewModel() {
    val users: Flow<PagingData<UserGenericUiModel>> = getRandomUsersWithRoomUseCase
        .run()
        .map { paging: PagingData<UserModel> ->
            paging.map { user -> user.toGenericUiModel() }
        }
        .cachedIn(viewModelScope)
}