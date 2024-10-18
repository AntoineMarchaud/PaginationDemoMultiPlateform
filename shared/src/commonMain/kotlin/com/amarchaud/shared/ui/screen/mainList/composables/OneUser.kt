package com.amarchaud.shared.ui.screen.mainList.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.amarchaud.shared.ui.composables.ImageLoaderSubCompose
import com.amarchaud.shared.ui.composables.ShimmerAnimationItem
import com.amarchaud.shared.ui.screen.mainList.heightOneCell
import com.amarchaud.shared.ui.screen.mainList.mockUser
import com.amarchaud.shared.ui.screen.mainList.models.UserGenericUiModel
import com.amarchaud.shared.ui.theme.PaginationDemoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import paginationdemokmm.shared.generated.resources.Res
import paginationdemokmm.shared.generated.resources.ic_error_24dp

@Composable
internal fun OneUser(
    modifier: Modifier = Modifier,
    user: UserGenericUiModel,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageLoaderSubCompose(
            modifier = Modifier
                .height(heightOneCell)
                .aspectRatio(ratio = 1f)
                .clip(shape = CircleShape)
                .border(width = 2.dp, color = Color.Blue, shape = CircleShape),
            data = user.imageUrl,
            loading = {
                ShimmerAnimationItem()
            },
            failure = {
                Image(
                    painter = painterResource(Res.drawable.ic_error_24dp),
                    contentDescription = "error loading image"
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = user.completeName)

        Divider(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 32.dp)
        )
    }
}

@Preview
@Composable
private fun OneUserPreview() {
    PaginationDemoTheme {
        OneUser(
            modifier = Modifier,
            user = mockUser,
            onClick = {}
        )
    }
}