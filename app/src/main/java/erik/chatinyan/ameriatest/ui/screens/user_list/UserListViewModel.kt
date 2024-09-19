package erik.chatinyan.ameriatest.ui.screens.user_list

import android.app.Application
import erik.chatinyan.ameriatest.ui.base.BaseViewModel

class UserListViewModel(
    application: Application
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
}
