package erik.chatinyan.ameriatest.ui.screens.user_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import erik.chatinyan.ameriatest.R
import erik.chatinyan.ameriatest.SCREEN_USER_DETAILS
import erik.chatinyan.ameriatest.ui.components.ErrorDialog
import erik.chatinyan.ameriatest.ui.components.ItemUser
import org.koin.androidx.compose.getViewModel

@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = getViewModel()
) {

    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                UserListViewModel.Effect.ShowNetworkError -> {
                    errorMessage = "Network error. Please check your connection."
                }

                is UserListViewModel.Effect.ShowError -> {
                    errorMessage = effect.message
                }

                is UserListViewModel.Effect.NavigateToUserDetails -> {
                    navController.navigate(SCREEN_USER_DETAILS + "/${effect.login}")
                }
            }
        }
    }

    val uiState = viewModel.uiState.collectAsState().value

    UserListScreenContent(
        uiState,
        obtainAction = {
            viewModel.obtainAction(it)
        }
    )

    errorMessage?.let { message ->
        ErrorDialog(
            message = message,
            onDismiss = {
                errorMessage = null
                viewModel.obtainAction(UserListViewModel.Action.RefreshData)
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreenContent(
    uiState: UserListViewModel.State = UserListViewModel.State(),
    obtainAction: (UserListViewModel.Action) -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Github")
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )
    }, content = { paddingValues ->
        LazyColumn(contentPadding = paddingValues, modifier = Modifier.fillMaxSize()) {
            items(items = uiState.list) { userItem ->
                ItemUser(
                    modifier = Modifier.fillMaxWidth(),
                    username = userItem.login,
                    id = userItem.id,
                    imageUrl = userItem.avatarUrl,
                    onNextClick = {
                        obtainAction(UserListViewModel.Action.UserItemClicked(userItem.login))
                    },
                    onItemClick = {
                        obtainAction(UserListViewModel.Action.UserItemClicked(userItem.login))
                    }
                )
            }
        }
    })
}