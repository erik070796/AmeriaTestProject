package erik.chatinyan.ameriatest

import android.app.Application
import erik.chatinyan.ameriatest.ui.screens.user_details.UserDetailsViewModel
import erik.chatinyan.ameriatest.ui.screens.user_list.UserListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {

    private val viewModelModule = module {
        viewModel { UserListViewModel(androidApplication()) }
        viewModel { UserDetailsViewModel(androidApplication()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                viewModelModule

            )
        }

    }
}