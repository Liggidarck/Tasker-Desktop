package com.george.tasker.desktop.controller

import com.george.tasker.desktop.model.Note
import tornadofx.Controller
import tornadofx.SortedFilteredList

class NotesController : Controller() {

    var listOfNotes = SortedFilteredList<Note>()
    fun addNote(title: String, description: String, dateCreate: String) {
        listOfNotes.add(Note(title, description, dateCreate))
    }

}