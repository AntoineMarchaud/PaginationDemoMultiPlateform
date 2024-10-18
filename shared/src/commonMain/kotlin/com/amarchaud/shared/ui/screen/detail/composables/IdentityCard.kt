package com.amarchaud.shared.ui.screen.detail.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import com.amarchaud.shared.ui.screen.detail.models.UserDetailUiModel
import org.jetbrains.compose.resources.painterResource
import paginationdemokmm.shared.generated.resources.Res
import paginationdemokmm.shared.generated.resources.ic_phone_call
import paginationdemokmm.shared.generated.resources.ic_profil_24
import paginationdemokmm.shared.generated.resources.ic_search

@Composable
fun IdentityCard(
    modifier: Modifier = Modifier,
    user: UserDetailUiModel
) {
    // todo CMM
    // val callPermission = rememberPermissionState(Manifest.permission.CALL_PHONE)
    val context = LocalPlatformContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Res.drawable.ic_profil_24),
                contentDescription = "",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = user.mainInfo.completeName)
        }
        Divider()
        Text(text = user.mainInfo.email)
        Divider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = user.address)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    //val map = "http://maps.google.com/maps?q=loc:${ user.coordinates.latitude},${user.coordinates.longitude}"
                    val map = "https://maps.google.co.in/maps?q=${user.address}"
                    //val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(map))
                    //context.startActivity(mapIntent)
                },
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = "",
                tint = Color.Black
            )
        }
        Divider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = user.phoneNumber)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier.clickable {
                    /*
                    if (callPermission.status.isGranted) {
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.setData(Uri.parse("tel:${user.phoneNumber}"))
                        context.startActivity(callIntent)
                    } else {
                        callPermission.launchPermissionRequest()
                    }*/
                },
                painter = painterResource(Res.drawable.ic_phone_call),
                contentDescription = "",
                tint = Color.Black
            )
        }
        Divider()
        Text(text = user.birthday)
    }
}