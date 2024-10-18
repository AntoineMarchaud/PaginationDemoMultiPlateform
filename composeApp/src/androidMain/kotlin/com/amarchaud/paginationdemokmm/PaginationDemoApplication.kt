package com.amarchaud.paginationdemokmm

import android.app.Application
import com.amarchaud.shared.data.di.daoModule
import com.amarchaud.shared.data.di.networkModule
import com.amarchaud.shared.data.di.repoModule
import com.amarchaud.shared.domain.di.useCaseModule
import com.amarchaud.shared.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class PaginationDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(PrintLogger(Level.DEBUG))
            androidContext(this@PaginationDemoApplication)
            modules(networkModule, daoModule, repoModule, useCaseModule, viewModelModule)
        }
    }
}