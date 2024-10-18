package com.amarchaud.shared.ui.screen.mappers

import com.amarchaud.shared.domain.models.UserModel
import com.amarchaud.shared.ui.screen.mainList.models.UserGenericUiModel

internal fun UserModel.toGenericUiModel() = UserGenericUiModel(
    localId = this.localId,
    completeName = "${this.name?.title} ${this.name?.first} ${this.name?.last}",
    email = this.email.orEmpty(),
    imageUrl = this.picture?.medium.orEmpty()
)