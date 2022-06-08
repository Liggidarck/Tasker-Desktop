package com.george.tasker.desktop.controller

import com.george.tasker.desktop.model.Note
import tornadofx.Controller
import tornadofx.SortedFilteredList

class NotesController : Controller() {

    var listOfNotes = SortedFilteredList<Note>()
    fun addNote(note: Note) {
        listOfNotes.add(note)
    }

    fun deleteNote(note: Note) {
        listOfNotes.remove(note)
    }

    fun editNote(oldNote: Note, newNote: Note) {
        listOfNotes.remove(oldNote)
        listOfNotes.add(newNote)
    }

}