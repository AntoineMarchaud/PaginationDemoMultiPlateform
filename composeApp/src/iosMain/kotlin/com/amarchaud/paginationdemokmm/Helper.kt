package com.amarchaud.paginationdemokmm

import com.amarchaud.shared.data.di.daoModule
import com.amarchaud.shared.data.di.networkModule
import com.amarchaud.shared.data.di.repoModule
import com.amarchaud.shared.domain.di.useCaseModule
import com.amarchaud.shared.ui.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(networkModule, daoModule, repoModule, useCaseModule, viewModelModule)
    }
}