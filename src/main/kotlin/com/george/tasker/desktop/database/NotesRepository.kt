package com.george.tasker.desktop.database

import com.george.tasker.desktop.model.Note
import tornadofx.SortedFilteredList
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class NotesRepository : NotesDatabase {

    private var connection: Connection? = null
    private lateinit var statement: Statement

    fun open() {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:notes.db")
            println("connected")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun close() {
        try {
            connection!!.close()
            println("disconnected")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun save(note: Note) {
        try {
            val request = "INSERT INTO notes (name, description) VALUES ('${note.title}', '${note.description}');"
            statement = connection!!.createStatement()
            statement.executeUpdate(request)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun update(note: Note) {
        try {
            val request =
                "UPDATE notes SET name = '${note.title}', description = '${note.description}' WHERE id = ${note.id};"
            println(request)
            statement.connection!!.createStatement()
            statement.execute(request)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun delete(id: Int) {
        try {
            val request = "DELETE FROM notes WHERE id = $id;"
            statement = connection!!.createStatement()
            statement.executeUpdate(request)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun getAllNotes(): SortedFilteredList<Note> {
        val listOfNotes = SortedFilteredList<Note>()
        try {
            val request = "SELECT id, name, description FROM notes;"
            statement = connection!!.createStatement()
            val resultSet = statement.executeQuery(request)
            println("-------------")
            while (resultSet.next()) {
                val id = resultSet.getInt("id")
                val name = resultSet.getString("name")
                val description = resultSet.getString("description")
                println("$id $name $description")
                val note = Note(id, name, description)
                listOfNotes.add(note)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return listOfNotes
    }


}