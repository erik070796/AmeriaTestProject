package erik.chatinyan.ameriatest.ui.screens.user_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel

@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel = getViewModel()
) {
    UserDetailsScreenContent()
}

@Preview
@Composable
fun UserDetailsScreenContent(){
    Text("UserDetailsScreenContent")

}