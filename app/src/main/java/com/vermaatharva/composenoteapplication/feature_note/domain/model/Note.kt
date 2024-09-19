package com.vermaatharva.composenoteapplication.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vermaatharva.composenoteapplication.ui.theme.BabyBlue
import com.vermaatharva.composenoteapplication.ui.theme.LightGreen
import com.vermaatharva.composenoteapplication.ui.theme.RedOrange
import com.vermaatharva.composenoteapplication.ui.theme.RedPink
import com.vermaatharva.composenoteapplication.ui.theme.Violet

@Entity
data class Note(
    val title:String,
    val content:String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id:Int?=null
){
    companion object{
        val noteColors= listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
class InvalidNoteException(message:String):Exception(message)
