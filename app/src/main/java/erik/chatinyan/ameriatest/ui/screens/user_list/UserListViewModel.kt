package erik.chatinyan.ameriatest.ui.screens.user_list

import android.app.Application
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.lifecycle.viewModelScope
import erik.chatinyan.ameriatest.data.remote.ResultWrapper
import erik.chatinyan.ameriatest.data.repo.UserRepository
import erik.chatinyan.ameriatest.di.viewModelModule
import erik.chatinyan.ameriatest.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel(
    application: Application,
    private val userRepository: UserRepository,
    ) : BaseViewModel<UserListViewModel.Action, UserListViewModel.Effect, UserListViewModel.State>(
    application = application,
    initialState = State()
) {
    data class State(
        val list: List<String> = emptyList(),
    )

    sealed class Action {
        object EmptyAction : Action()
    }

    sealed class Effect {
        object ShowLoading : Effect()
    }

    override fun handleAction(action: Action) {
        when (action) {
            is Action.EmptyAction -> {
            }
        }
    }

    init {
        getUserList()
    }

    fun getUserList(){
        viewModelScope.launch {
            val response = userRepository.getUserList()

            when (response) {
                is ResultWrapper.GenericError ->{
                    // show error
                }

                is ResultWrapper.NetworkException -> {
                   // show network error
                }
                is ResultWrapper.Success -> {
                  val userList = response.value
                    userList
                }
            }
        }
    }
}
