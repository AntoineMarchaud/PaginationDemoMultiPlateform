package com.amarchaud.shared.ui.screen.detail.mappers

import com.amarchaud.shared.domain.models.UserModel
import com.amarchaud.shared.ui.screen.detail.models.UserDetailUiModel
import com.amarchaud.shared.ui.screen.mappers.toGenericUiModel

internal fun UserModel.toDetailUiModel() = UserDetailUiModel(
    mainInfo = this.toGenericUiModel(),
    mainImageUrl = this.picture?.large.orEmpty(),
    coordinates = Pair(
        this.location?.coordinates?.latitude?.toDouble() ?: 0.0,
        this.location?.coordinates?.longitude?.toDouble() ?: 0.0
    ),
    address = "${this.location?.street?.number} ${this.location?.street?.name} ${this.location?.city}",
    phoneNumber = "${this.phone}",
    birthday = "${this.dob?.date?.toString()}" // todo ama
)