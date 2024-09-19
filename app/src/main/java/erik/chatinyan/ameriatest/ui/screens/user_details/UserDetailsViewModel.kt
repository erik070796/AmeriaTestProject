package erik.chatinyan.ameriatest.ui.screens.user_details

import android.app.Application
import erik.chatinyan.ameriatest.ui.base.BaseViewModel

class UserDetailsViewModel(
    application: Application
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
}
