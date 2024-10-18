package com.amarchaud.shared.ui.screen.mappers

import com.amarchaud.shared.domain.models.ErrorApiModel
import com.amarchaud.shared.ui.screen.mainList.models.ErrorApiUiModel

internal fun ErrorApiModel.toErrorUiModel() = when (this) {
    is ErrorApiModel.ApiGenericServerError -> ErrorApiUiModel.ApiGenericServerError
    is ErrorApiModel.ApiNullBody -> ErrorApiUiModel.ApiNullBody
    is ErrorApiModel.ApiServerErrorWithCode -> ErrorApiUiModel.ApiServerErrorWithCode(
        responseCode = this.responseCode,
        responseMessage = this.responseMessage
    )

    is ErrorApiModel.NoInternetError -> ErrorApiUiModel.NoInternetError
    is ErrorApiModel.SocketTimeOutError -> ErrorApiUiModel.SocketTimeOutError
    is ErrorApiModel.GenericError -> ErrorApiUiModel.GenericError
}