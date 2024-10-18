package com.amarchaud.shared.ui.screen.detail.models

import com.amarchaud.shared.ui.screen.mainList.models.UserGenericUiModel

data class UserDetailUiModel(
    // same as UserUiModel
    val mainInfo: UserGenericUiModel = UserGenericUiModel(),
    // plus a lot of info
    val mainImageUrl: String = String(),
    val coordinates: Pair<Double, Double> = Pair(0.0, 0.0),
    val address: String = String(),
    val phoneNumber: String = String(),
    val birthday: String = String()
)