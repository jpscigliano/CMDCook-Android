package co.cmd.cook.presentation.recipeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cmd.cook.presentation.recipeDetail.RecipeDetailViewModel.Event.ShowError
import co.cmd.core.domain.ID
import co.cmd.core.domain.RecipeDetail
import co.cmd.core.interactors.GetRecipeDetail
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val getRecipeDetail: GetRecipeDetail) : ViewModel() {

    private val _LoadingViewState = MutableStateFlow(false)
    val loadingViewState: Flow<Boolean> = _LoadingViewState

    private val _RecipeDetailViewState = MutableStateFlow(RecipeDetailViewState())
    val recipeDetailViewState: Flow<RecipeDetailViewState> = _RecipeDetailViewState

    private val _MessageEvents = Channel<Event>(capacity = Channel.CONFLATED)

    // Exposed with receiveAsFlow to make sure that only one observer receives updates.
    val messageEvents = _MessageEvents.receiveAsFlow()

    fun fetchData(recipeID: ID) {
        viewModelScope.launch {
            _LoadingViewState.emit(true)
            val recipeResult = getRecipeDetail(recipeID)
            if (recipeResult.isSuccess) {
                recipeResult.getOrNull()?.let {
                    _RecipeDetailViewState.emit(_RecipeDetailViewState.value.copy(recipeDetail = it))
                } ?: let {
                    // If null the UI can be updated with any kind of error.
                }
            } else {
                //An event can be sent to the UI with the error.
                _MessageEvents.trySend(ShowError(recipeResult.exceptionOrNull()?.message ?: ""))
            }
            _LoadingViewState.emit(false)
        }
    }

    //ViewState should be properly define here. For now im going to be lazy dev and pass all the object.
    data class RecipeDetailViewState(
        val recipeDetail: RecipeDetail? = null,
        val showServings: Boolean = false
    )

    sealed class Event {
        class ShowError(val error: String) : Event()
    }
}