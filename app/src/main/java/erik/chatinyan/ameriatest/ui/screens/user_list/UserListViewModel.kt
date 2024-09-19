package erik.chatinyan.ameriatest.ui.screens.user_list

import android.app.Application
import androidx.lifecycle.viewModelScope
import erik.chatinyan.ameriatest.data.remote.ResultWrapper
import erik.chatinyan.ameriatest.data.remote.model.UserListItem
import erik.chatinyan.ameriatest.data.repo.UserRepository
import erik.chatinyan.ameriatest.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserListViewModel(
    application: Application,
    private val userRepository: UserRepository,
) : BaseViewModel<UserListViewModel.Action, UserListViewModel.Effect, UserListViewModel.State>(
    application = application,
    initialState = State()
) {
    data class State(
        val list: List<UserListItem> = emptyList(),
    )

    sealed class Action {
        object RefreshData : Action()
        class UserItemClicked(val login: String) : Action()
    }

    sealed class Effect {
        object ShowNetworkError : Effect()
        class ShowError(val code: Int, val message: String) : Effect()
        class NavigateToUserDetails(val login: String) : Effect()
    }

    override fun handleAction(action: Action) {
        when (action) {
            is Action.UserItemClicked -> {
                obtainEffect {
                    Effect.NavigateToUserDetails(action.login)
                }
            }
            Action.RefreshData ->{
                viewModelScope.launch {
                    delay(700)
                    getUserList()
                }
            }
        }
    }

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            val response = userRepository.getUserList()

            when (response) {
                is ResultWrapper.GenericError -> {
                    obtainEffect {
                        Effect.ShowError(response.code ?: 0, response.error ?: "Empty Error")
                    }
                }

                is ResultWrapper.NetworkException -> {
                    obtainEffect {
                        Effect.ShowNetworkError
                    }
                }
                is ResultWrapper.Success -> {
                    val userList = response.value
                    obtainState {
                        copy(list = userList)
                    }
                }
            }
        }
    }
}
