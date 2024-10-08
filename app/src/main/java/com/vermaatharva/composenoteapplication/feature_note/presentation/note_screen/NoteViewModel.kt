package com.vermaatharva.composenoteapplication.feature_note.presentation.note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vermaatharva.composenoteapplication.feature_note.domain.model.Note
import com.vermaatharva.composenoteapplication.feature_note.domain.use_case.NoteUseCases
import com.vermaatharva.composenoteapplication.feature_note.domain.util.NoteOrder
import com.vermaatharva.composenoteapplication.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
):ViewModel(){

    private val _state= mutableStateOf(NotesState())
    val state:State<NotesState> = _state
    private var recentlyDeleteNote: Note?=null
    private var noteJob: Job?=null

    init{
        getNotes(NoteOrder.Date(OrderType.Descending))
    }
    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.DeleteNote ->{
                viewModelScope.launch {
                    noteUseCases.deleteNotes(event.note)
                    recentlyDeleteNote=event.note
                }
            }
            is NotesEvent.Order ->{
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.RestoreNote ->{
                viewModelScope.launch {
                    noteUseCases.addNotes(recentlyDeleteNote?: return@launch)
                    recentlyDeleteNote=null
                }
            }
            is NotesEvent.ToggleOrderSection ->{
                _state.value=state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }

    }
    private fun getNotes(noteOrder: NoteOrder){
        noteJob?.cancel()
        noteJob=noteUseCases.getNotes(noteOrder).onEach {
            _state.value=state.value.copy(notes = it,noteOrder=noteOrder)
        }.launchIn(viewModelScope)
    }
}