package com.amarchaud.shared.data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.amarchaud.database.PaginationDemoDatabase
import com.amarchaud.shared.data.db.PaginationDemoDao
import com.amarchaud.shared.data.db.PaginationDemoDaoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


actual val daoModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = PaginationDemoDatabase.Schema,
            context = androidContext(),
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