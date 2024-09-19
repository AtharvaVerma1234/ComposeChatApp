package com.vermaatharva.composenoteapplication.feature_note.domain.use_case

import com.vermaatharva.composenoteapplication.feature_note.domain.model.InvalidNoteException
import com.vermaatharva.composenoteapplication.feature_note.domain.model.Note
import com.vermaatharva.composenoteapplication.feature_note.domain.repository.NoteRepository

class AddNotes(private val repository: NoteRepository) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The Title is empty")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The Content is empty")
        }
        repository.insertNote(note)
    }
}