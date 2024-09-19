package erik.chatinyan.ameriatest.ui.screens.user_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserDetailsScreen(
    login:String,
    navController: NavHostController,
    viewModel: UserDetailsViewModel = getViewModel(
        parameters = {
            parametersOf(
                login
            )
        }
    )
) {
    UserDetailsScreenContent()
}

@Preview
@Composable
fun UserDetailsScreenContent(){
    Text("UserDetailsScreenContent")

}