package com.amarchaud.shared.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.amarchaud.database.PaginationDemoDatabase
import com.amarchaud.shared.data.db.PaginationDemoDao
import com.amarchaud.shared.data.db.PaginationDemoDaoImpl
import org.koin.core.module.Module
import org.koin.dsl.module

actual val daoModule: Module = module {

    single<SqlDriver> {
        NativeSqliteDriver(
            schema = PaginationDemoDatabase.Schema,
            name = "PaginationDemoDatabase.db"
        )
    }

    single {
        PaginationDemoDatabase(
            driver = get()
        )
    }
    single<PaginationDemoDao> {
        PaginationDemoDaoImpl(
            database = get()
        )
    }
}
