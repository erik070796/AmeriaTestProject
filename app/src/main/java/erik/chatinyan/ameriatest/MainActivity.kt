package erik.chatinyan.ameriatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    mainNavController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier, navController = mainNavController, startDestination = SCREEN_USER_LIST
    ) {
        composable(SCREEN_USER_LIST) {
            UserListScreen()
        }
        composable(SCREEN_USER_DETAILS) {
            UserDetailsScreen()
        }
    }
}

const val SCREEN_USER_LIST = "userlist"
const val SCREEN_USER_DETAILS = "userlist"