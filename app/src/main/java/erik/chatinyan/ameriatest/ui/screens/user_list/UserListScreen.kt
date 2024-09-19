package erik.chatinyan.ameriatest.ui.screens.user_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel

@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = getViewModel()
) {
    UserListScreenContent()
}

@Preview
@Composable
fun UserListScreenContent(){
    Text("UserListScreenContent")
}