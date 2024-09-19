package erik.chatinyan.ameriatest.ui.screens.user_details

import android.app.Application
import androidx.lifecycle.viewModelScope
import erik.chatinyan.ameriatest.data.remote.ResultWrapper
import erik.chatinyan.ameriatest.data.repo.UserRepository
import erik.chatinyan.ameriatest.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    application: Application,
    private val userRepository: UserRepository,
    private val login:String
) : BaseViewModel<UserDetailsViewModel.Action, UserDetailsViewModel.Effect, UserDetailsViewModel.State>(
    application = application,
    initialState = State()
) {
    data class State(
        val name: String = "",
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


    fun getUserDetails(){

        viewModelScope.launch {
            val response = userRepository.getUserDetails(login)

            when (response) {
                is ResultWrapper.GenericError ->{
                    // show error
                }

                is ResultWrapper.NetworkException -> {
                    // show network error
                }
                is ResultWrapper.Success -> {
                    val userDetails = response.value
                    userDetails
                }
            }
        }
    }


}
