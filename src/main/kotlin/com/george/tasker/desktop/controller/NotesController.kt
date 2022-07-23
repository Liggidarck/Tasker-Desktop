package com.george.tasker.desktop.controller

import com.george.tasker.desktop.database.NotesRepository
import com.george.tasker.desktop.model.Note
import tornadofx.Controller
import tornadofx.SortedFilteredList

class NotesController : Controller() {

    var listOfNotes = SortedFilteredList<Note>()
    private var repository: NotesRepository = NotesRepository()

    fun openConnection() {
        repository.open()
    }

    fun closeConnection() {
        repository.close()
    }

    fun addNote(note: Note) {
        repository.save(note)
        getNotes()
    }
    fun deleteNote(id: Int) {
        repository.delete(id)
        getNotes()
    }
    fun editNote(note: Note) {
        repository.update(note)
        getNotes()
    }
    fun getNotes() {
        listOfNotes.clear()
        listOfNotes.addAll(repository.getAllNotes())
    }
}