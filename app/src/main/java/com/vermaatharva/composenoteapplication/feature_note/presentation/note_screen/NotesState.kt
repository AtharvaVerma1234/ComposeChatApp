package com.vermaatharva.composenoteapplication.feature_note.presentation.note_screen

import com.vermaatharva.composenoteapplication.feature_note.domain.model.Note
import com.vermaatharva.composenoteapplication.feature_note.domain.util.NoteOrder
import com.vermaatharva.composenoteapplication.feature_note.domain.util.OrderType

data class NotesState(
    val notes:List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean = false
)
