package com.vermaatharva.composechatapplication.di

import android.app.Application
import androidx.room.Room
import com.vermaatharva.composechatapplication.feature_note.data.data_source.NoteDatabase
import com.vermaatharva.composechatapplication.feature_note.data.repository.NoteRepositoryImpl
import com.vermaatharva.composechatapplication.feature_note.domain.repository.NoteRepository
import com.vermaatharva.composechatapplication.feature_note.domain.use_case.AddNotes
import com.vermaatharva.composechatapplication.feature_note.domain.use_case.DeleteNotes
import com.vermaatharva.composechatapplication.feature_note.domain.use_case.GetNote
import com.vermaatharva.composechatapplication.feature_note.domain.use_case.GetNotes
import com.vermaatharva.composechatapplication.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(app,NoteDatabase::class.java,NoteDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db:NoteDatabase):NoteRepository{
//        return fakeNoteRepository(db.noteDao) for testcases
        return NoteRepositoryImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            GetNotes(repository),
            DeleteNotes(repository),
            AddNotes(repository),
            GetNote(repository)
        )
    }


}