package erik.chatinyan.ameriatest.di

import erik.chatinyan.ameriatest.data.remote.provideApi
import erik.chatinyan.ameriatest.data.remote.provideRetrofit
import erik.chatinyan.ameriatest.data.repo.UserRepository
import erik.chatinyan.ameriatest.ui.screens.user_details.UserDetailsViewModel
import erik.chatinyan.ameriatest.ui.screens.user_list.UserListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserListViewModel(androidApplication(), get()) }
    viewModel { parameters ->
        UserDetailsViewModel(androidApplication(), get(), parameters.get())
    }
}

val networkModule = module {
    factory { provideApi(get()) }
    single { provideRetrofit() }
}

val repositoryModule = module {
    single { UserRepository(get()) }
}