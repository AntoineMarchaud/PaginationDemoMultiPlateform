package com.amarchaud.shared.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.amarchaud.shared.domain.models.UserModel
import com.amarchaud.shared.domain.repository.PaginationDemoRepository
import com.amarchaud.shared.data.db.PaginationDemoDao
import com.amarchaud.shared.data.mappers.toDomain
import com.amarchaud.shared.data.repository.remotemediator.RandomUsersRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PaginationDemoRepositoryImpl(
    private val paginationDemoDao: PaginationDemoDao,
    private val paginationDemoApi: com.amarchaud.shared.data.api.PaginationDemoApi
) : PaginationDemoRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRandomUsersRoom(): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        remoteMediator = RandomUsersRemoteMediator(
            paginationDemoDao = paginationDemoDao,
            paginationDemoApi = paginationDemoApi
        ),
        pagingSourceFactory = {
            paginationDemoDao.getUsersPagingSource()
        }
    ).flow.map {
        it.map { oneUserEntity -> oneUserEntity.toDomain() }
    }

    override suspend fun getUserFromCache(localId: Long) =
        paginationDemoDao.getUserFromCache(localId)?.toDomain()
}