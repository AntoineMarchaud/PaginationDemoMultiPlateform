package com.amarchaud.shared.ui.screen.mainList.models

sealed class ErrorApiUiModel {
    class ApiServerErrorWithCode(val responseCode: Int, val responseMessage: String) :
        ErrorApiUiModel()

    data object ApiNullBody : ErrorApiUiModel()
    data object ApiGenericServerError : ErrorApiUiModel()
    data object SocketTimeOutError : ErrorApiUiModel()
    data object NoInternetError : ErrorApiUiModel()
    data object GenericError : ErrorApiUiModel()
}
/*

fun ErrorApiModel.toMessage(context: PlatformContext): String {
    return when (this.toErrorUiModel()) {
        is ErrorApiUiModel.ApiGenericServerError -> context.getString(Res.string.error_ApiGenericServerError)
        is ErrorApiUiModel.ApiNullBody -> context.getString(Res.string.error_ApiNullBody)
        is ErrorApiUiModel.ApiServerErrorWithCode -> context.getString(Res.string.error_ApiServerErrorWithCode)
        is ErrorApiUiModel.GenericError -> context.getString(Res.string.error_GenericError)
        is ErrorApiUiModel.NoInternetError -> context.getString(Res.string.error_NoInternetError)
        is ErrorApiUiModel.SocketTimeOutError -> context.getString(Res.string.error_SocketTimeOutError)
        else -> context.getString(Res.string.error_ApiGenericServerError)
    }
}*/
