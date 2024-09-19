package com.vermaatharva.composenoteapplication.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vermaatharva.composenoteapplication.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.vermaatharva.composenoteapplication.feature_note.presentation.note_screen.NotesScreen
import com.vermaatharva.composenoteapplication.feature_note.presentation.util.Screen
import com.vermaatharva.composenoteapplication.ui.theme.ComposeChatApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController= rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.NoteScreen.route
                        ){
                            composable(route=Screen.NoteScreen.route){
                                NotesScreen(navController = navController)
                            }
                            composable(
                                route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(name="noteId"){
                                        type=NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(name="noteColor"){
                                        type=NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ){
                                val color=it.arguments?.getInt("noteColor")?:-1
                                AddEditNoteScreen(navController = navController,
                                    noteColor=color)
                            }
                    }
                }
            }
        }
    }
}
