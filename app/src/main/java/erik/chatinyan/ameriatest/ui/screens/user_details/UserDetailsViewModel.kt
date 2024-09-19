package erik.chatinyan.ameriatest.ui.screens.user_details

import android.app.Application
import androidx.lifecycle.viewModelScope
import erik.chatinyan.ameriatest.data.remote.ResultWrapper
import erik.chatinyan.ameriatest.data.repo.UserRepository
import erik.chatinyan.ameriatest.ui.base.BaseViewModel
import erik.chatinyan.ameriatest.ui.screens.user_list.UserListViewModel.Action
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class UserDetailsViewModel(
    application: Application,
    private val userRepository: UserRepository,
    private val login: String
) : BaseViewModel<UserDetailsViewModel.Action, UserDetailsViewModel.Effect, UserDetailsViewModel.State>(
    application = application,
    initialState = State()
) {
    data class State(
        val name: String = "",
        val location: String = "",
        val followers: String = "",
        val following: String = "",
        val bio: String = "",
        val avatarUrl: String = "",
        val repoCount: String = "",
        val gistCount: String = "",
        val updatedAt: String = "",
    )

    sealed class Action {
        object OnBackPressed : Action()
        object RefreshData : Action()
    }

    sealed class Effect {
        object OnBackPressed : Effect()
        object ShowNetworkError : Effect()
        class ShowError(val code: Int, val message: String) : Effect()
    }

    override fun handleAction(action: Action) {
        when (action) {
            is Action.OnBackPressed -> {
                obtainEffect { Effect.OnBackPressed }
            }

            Action.RefreshData -> {
                viewModelScope.launch {
                    delay(700)
                    getUserDetails()
                }
            }
        }
    }

    init {
        getUserDetails()
    }

    private fun getUserDetails() {

        viewModelScope.launch {
            val response = userRepository.getUserDetails(login)

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
                    val userDetails = response.value
                    obtainState {
                        copy(
                            name = userDetails.name ?: "",
                            location = userDetails.location ?: "",
                            followers = (userDetails.followers ?: 0).toString(),
                            following = (userDetails.following ?: 0).toString(),
                            bio = userDetails.bio ?: "",
                            avatarUrl = userDetails.avatarUrl ?: "",
                            gistCount = (userDetails.publicGists ?: 0).toString(),
                            repoCount = (userDetails.publicRepos ?: 0).toString(),
                            updatedAt = getFormattedTime(userDetails.updatedAt)

                        )
                    }
                }
            }
        }
    }

    fun getFormattedTime(timestamp: String?): String {
        timestamp ?: return ""
        val zonedDateTime = ZonedDateTime.parse(timestamp)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - hh:mm a")
        return zonedDateTime.format(formatter)
    }
}
