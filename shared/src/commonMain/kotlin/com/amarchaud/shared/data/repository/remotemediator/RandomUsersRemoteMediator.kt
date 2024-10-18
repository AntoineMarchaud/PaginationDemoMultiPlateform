package com.amarchaud.shared.data.repository.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.amarchaud.database.UsersEntity
import com.amarchaud.shared.data.db.PaginationDemoDao
import com.amarchaud.shared.data.mappers.toDomain
import com.amarchaud.shared.data.mappers.toEntity
import com.amarchaud.shared.data.models.ErrorApiDataModel
import com.amarchaud.shared.data.models.PageEntityModel
import com.amarchaud.shared.domain.models.ErrorApiModel
import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalPagingApi::class)
class RandomUsersRemoteMediator(
    private val paginationDemoDao: PaginationDemoDao,
    private val paginationDemoApi: com.amarchaud.shared.data.api.PaginationDemoApi
) : RemoteMediator<Int, UsersEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UsersEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                paginationDemoDao.getLastPage()?.plus(1) ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }
        }

        runCatching {
            delay(300)
            paginationDemoApi.getRandomUsers(page = page).getOrThrow()
        }.fold(
            onSuccess = {
                if (it.users.isEmpty()) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                if (loadType == LoadType.REFRESH) {
                    paginationDemoDao.clearAll()
                    paginationDemoDao.clearPage()
                }

                // insert users in DB
                it.users.let { allUsersDataModel ->
                    paginationDemoDao.insertPage(page = PageEntityModel(it.info?.page ?: 1))
                    paginationDemoDao.insertAll(
                        allUsersDataModel.map { oneUserDataModel ->
                            oneUserDataModel.toEntity()
                        }
                    )
                }

                return MediatorResult.Success(endOfPaginationReached = false)
            },
            onFailure = {
                return when (it) {
                    is ErrorApiDataModel -> MediatorResult.Error(it.toDomain())
                    is CancellationException -> MediatorResult.Success(endOfPaginationReached = false) // special case when canceling current coroutine
                    else -> MediatorResult.Error(ErrorApiModel.GenericError())
                }
            }
        )
    }
}