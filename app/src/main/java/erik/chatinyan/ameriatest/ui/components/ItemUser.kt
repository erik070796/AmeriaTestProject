package erik.chatinyan.ameriatest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import erik.chatinyan.ameriatest.R

@Preview
@Composable
fun ItemUser(
    modifier: Modifier = Modifier,
    username: String = "",
    imageUrl: String? = "",
    id: Long = 0,
    onNextClick: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .clickable { onItemClick() }
        .background(Color.White)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(60.dp)
                    .clip(shape = CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(username, color = Color.Black)
                Text(stringResource(R.string.id, id))
            }
            IconButton(
                modifier = Modifier.padding(16.dp), onClick = onNextClick
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_navigate_next_24),
                    contentDescription = ""
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.LightGray)
        )
    }
}