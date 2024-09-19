package erik.chatinyan.ameriatest.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<Action, Effect, State>(initialState: State, application: Application) :
    AndroidViewModel(application) {

    protected val dispatcher: CoroutineContext = Dispatchers.IO

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _action: MutableSharedFlow<Action> = MutableSharedFlow()
    val action = _action.asSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            action.collect {
                handleAction(it)
            }
        }
    }

    abstract fun handleAction(action: Action)

    protected fun obtainState(reduce: State.() -> State) {
        _uiState.value = uiState.value.reduce()
    }

    protected fun obtainEffect(builder: () -> Effect) {
        viewModelScope.launch { _effect.send(builder()) }
    }

    fun obtainAction(action: Action) {
        viewModelScope.launch { _action.emit(action) }
    }
}
