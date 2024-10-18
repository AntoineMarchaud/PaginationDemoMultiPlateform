package com.amarchaud.shared.domain.repository

import androidx.paging.PagingData
import com.amarchaud.shared.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface PaginationDemoRepository {
    fun getRandomUsersRoom(): Flow<PagingData<UserModel>>
    suspend fun getUserFromCache(localId: Long): UserModel?
}