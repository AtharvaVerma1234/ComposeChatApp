package com.vermaatharva.composechatapplication.feature_note.domain.use_case

import com.vermaatharva.composechatapplication.feature_note.domain.model.Note
import com.vermaatharva.composechatapplication.feature_note.domain.repository.NoteRepository

class DeleteNotes(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
            repository.deleteNote(note)
    }

}