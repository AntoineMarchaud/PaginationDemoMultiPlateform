package com.amarchaud.shared.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amarchaud.shared.Greeting
import com.amarchaud.shared.ui.composables.ImageLoaderSubCompose
import com.amarchaud.shared.ui.composables.ShimmerAnimationItem
import com.amarchaud.shared.ui.screen.detail.composables.IdentityCard
import com.amarchaud.shared.ui.screen.detail.models.UserDetailUiModel
import com.amarchaud.shared.ui.screen.mainList.mockUser
import com.amarchaud.shared.ui.theme.PaginationDemoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import paginationdemokmm.shared.generated.resources.Res
import paginationdemokmm.shared.generated.resources.back_arrow
import paginationdemokmm.shared.generated.resources.ic_error_24dp

@Composable
fun UserDetailComposable(
    localId: Long, // todo need to be replace by KMM
    viewModel: UserDetailViewModel = koinViewModel(
        parameters = { parametersOf(localId) }
    ),
    onBack: () -> Unit
) {
    val user by viewModel.userDetailUiModel.collectAsState()

    /*
    // todo CMM
    BackHandler {
        onBack()
    }*/

    UserDetailScreen(
        user = user,
        onBack = onBack
    )
}

@Composable
private fun UserDetailScreen(
    user: UserDetailUiModel,
    onBack: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = Greeting().greet())

            ImageLoaderSubCompose(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                data = user.mainImageUrl,
                loading = {
                    ShimmerAnimationItem()
                },
                failure = {
                    Image(
                        painter = painterResource(Res.drawable.ic_error_24dp),
                        contentDescription = "error loading"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            IdentityCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                user = user
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (LocalInspectionMode.current) { // preview
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(ratio = 1f)
                        .background(color = Color.Black),
                    text = "this is a map",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            } else {
                /*
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(ratio = 1f)
                        .clipToBounds(),
                    factory = { context ->
                        // Creates the view
                        MapView(context).apply {
                            setTileSource(TileSourceFactory.USGS_TOPO)
                        }
                    },
                    update = { view ->
                        view.controller.setCenter(user.coordinates)
                        view.controller.setZoom(8.0)

                        Marker(view).apply {
                            this.position = user.coordinates
                            this.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                        }.also {
                            view.overlays.add(it)
                        }
                    }
                )*/
            }
        }

        OutlinedButton(
            onClick = onBack,
            shape = CircleShape,
            border = BorderStroke(
                2.dp,
                Color.White,
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(start = 16.dp, top = 16.dp)
                .size(56.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            contentPadding = PaddingValues(8.dp),
        ) {
            Icon(
                painter = painterResource(Res.drawable.back_arrow),
                contentDescription = "back",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun UserDetailScreenPreview() {
    PaginationDemoTheme {
        UserDetailScreen(
            user = UserDetailUiModel(
                mainInfo = mockUser,
            ),
            onBack = {}
        )
    }
}