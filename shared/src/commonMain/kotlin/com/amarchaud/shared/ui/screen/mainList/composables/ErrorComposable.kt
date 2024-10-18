package com.amarchaud.shared.ui.screen.mainList.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.amarchaud.shared.ui.theme.PaginationDemoTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import paginationdemokmm.shared.generated.resources.Res
import paginationdemokmm.shared.generated.resources.refresh

@Composable
internal fun ErrorComposable(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefresh: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = errorMessage
        )

        Button(onClick = onRefresh) {
            Text(text = stringResource(Res.string.refresh))
        }
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    PaginationDemoTheme {
        ErrorComposable(
            errorMessage = "An error",
            onRefresh = {}
        )
    }
}