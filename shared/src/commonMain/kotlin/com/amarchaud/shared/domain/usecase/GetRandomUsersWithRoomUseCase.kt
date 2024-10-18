package com.amarchaud.shared.domain.usecase

import com.amarchaud.shared.domain.repository.PaginationDemoRepository

class GetRandomUsersWithRoomUseCase(
    private val repository: PaginationDemoRepository
) {
    fun run() = repository.getRandomUsersRoom()
}