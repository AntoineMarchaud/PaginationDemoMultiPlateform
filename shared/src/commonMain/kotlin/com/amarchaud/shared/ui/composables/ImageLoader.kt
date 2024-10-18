package com.amarchaud.shared.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil3.Image
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import paginationdemokmm.shared.generated.resources.Res
import paginationdemokmm.shared.generated.resources.ic_error_24dp

@Composable
fun ImageLoaderSubCompose(
    modifier: Modifier = Modifier,
    data: Any?,
    contentScale: ContentScale = ContentScale.FillHeight,
    contentDescription: String? = null,
    loading: @Composable (BoxScope.() -> Unit)? = null,
    failure: @Composable (BoxScope.() -> Unit)? = null,
    success: @Composable (BoxScope.(Image) -> Unit)? = null,
    onSuccess: (AsyncImagePainter.State.Success) -> Unit = {},
    onError: (AsyncImagePainter.State.Error) -> Unit = {},
) {
    if (LocalInspectionMode.current) { // preview
        Image(
            modifier = modifier,
            painter = painterResource(Res.drawable.ic_error_24dp),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
    } else {
        val context = LocalPlatformContext.current
        SubcomposeAsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(context = context)
                .data(data)
                .crossfade(500)
                .build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            // We use our own signature, that's why we get an additional lambda
            // We don't want to create a dependence with a specific library scope
            loading = loading?.run {
                { loading() }
            },
            error = failure?.run {
                { failure() }
            },
            success = success?.run {
                { successState -> success(successState.result.image) }
            },
            onSuccess = onSuccess,
            onError = onError,
        )
    }
}

@Composable
@Preview
private fun ImageLoaderPreview() {
    ImageLoaderSubCompose(data = "") {}
}