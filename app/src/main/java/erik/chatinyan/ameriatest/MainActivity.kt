package erik.chatinyan.ameriatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import erik.chatinyan.ameriatest.ui.screens.user_details.UserDetailsScreen
import erik.chatinyan.ameriatest.ui.screens.user_list.UserListScreen
import erik.chatinyan.ameriatest.ui.theme.AmeriaTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmeriaTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavHost()
                }
            }
        }
    }
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = SCREEN_USER_LIST
    ) {
        composable(SCREEN_USER_LIST) {
            UserListScreen(
                navController = navController
            )
        }
        composable(SCREEN_USER_DETAILS + "/{$ARGUMENT_SCREEN_USER_DETAILS_LOGIN}") { backstackEntry ->
            val login = backstackEntry.arguments?.getString(ARGUMENT_SCREEN_USER_DETAILS_LOGIN) ?: ""
            UserDetailsScreen(
                login = login,
                navController = navController
            )
        }
    }
}

const val SCREEN_USER_LIST = "user_list"
const val SCREEN_USER_DETAILS = "user_details"
const val ARGUMENT_SCREEN_USER_DETAILS_LOGIN = "login"