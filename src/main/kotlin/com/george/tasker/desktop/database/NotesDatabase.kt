package com.george.tasker.desktop.database

import com.george.tasker.desktop.model.Note
import tornadofx.SortedFilteredList

interface NotesDatabase {

    fun save(note: Note)

    fun update(note: Note)

    fun delete(id: Int)

    fun getAllNotes() : SortedFilteredList<Note>

}