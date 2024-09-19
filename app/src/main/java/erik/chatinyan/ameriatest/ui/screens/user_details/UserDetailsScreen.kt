package erik.chatinyan.ameriatest.ui.screens.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import erik.chatinyan.ameriatest.R
import erik.chatinyan.ameriatest.ui.components.ErrorDialog
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserDetailsScreen(
    login: String,
    navController: NavHostController,
    viewModel: UserDetailsViewModel = getViewModel(
        parameters = {
            parametersOf(
                login
            )
        }
    )
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                UserDetailsViewModel.Effect.OnBackPressed -> {
                    navController.navigateUp()
                }

                UserDetailsViewModel.Effect.ShowNetworkError -> {
                    errorMessage = "Network error. Please check your connection."
                }

                is UserDetailsViewModel.Effect.ShowError -> {
                    errorMessage = effect.message
                }
            }
        }
    }

    val uiState = viewModel.uiState.collectAsState().value

    UserDetailsScreenContent(
        uiState = uiState,
        obtainAction = {
            viewModel.obtainAction(it)
        }
    )

    errorMessage?.let { message ->
        ErrorDialog(
            message = message,
            onDismiss = {
                errorMessage = null
                viewModel.obtainAction(UserDetailsViewModel.Action.RefreshData)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreenContent(
    uiState: UserDetailsViewModel.State = UserDetailsViewModel.State(),
    obtainAction: (UserDetailsViewModel.Action) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.profile))
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(16.dp),
                        onClick = {
                            obtainAction(UserDetailsViewModel.Action.OnBackPressed)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = Color.White)
                    .shadow(elevation = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp)
                        .clip(shape = CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Text(uiState.name, color = Color.Black)

                if (uiState.location.isNotEmpty()) {
                    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_location_pin_24),
                            contentDescription = "",
                            tint = Color.Black
                        )
                        Text(uiState.location)
                    }
                }

                Spacer(Modifier.padding(8.dp))


                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(stringResource(R.string.followers, uiState.followers), color = Color.Black)
                    Spacer(
                        Modifier
                            .padding(horizontal = 8.dp)
                            .width(1.dp)
                            .height(16.dp)
                            .background(color = Color.LightGray)
                    )
                    Text(stringResource(R.string.following, uiState.following), color = Color.Black)
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.Start

                ) {
                    if (uiState.bio.isNotEmpty()) {
                        Text("Bio:\n${uiState.bio}",color = Color.Black)
                    }
                    if (uiState.repoCount.isNotEmpty()) {
                        Text("Public repository:\n${uiState.repoCount}",color = Color.Black)
                    }
                    if (uiState.gistCount.isNotEmpty()) {
                        Text("Public gists:\n${uiState.gistCount}",color = Color.Black)
                    }
                    if (uiState.updatedAt.isNotEmpty()) {
                        Text("Updated at:\n${uiState.updatedAt}",color = Color.Black)
                    }
                }


            }
        }
    )
}