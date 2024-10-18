package com.amarchaud.shared.domain.usecase

import com.amarchaud.shared.domain.repository.PaginationDemoRepository

class GetUserFromCacheUseCase (
    private val repository: PaginationDemoRepository
) {
    suspend fun run(localId: Long) = repository.getUserFromCache(localId)
}