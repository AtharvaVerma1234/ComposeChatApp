package com.vermaatharva.composenoteapplication.feature_note.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNotes: DeleteNotes,
    val addNotes: AddNotes,
    val getNote: GetNote
)