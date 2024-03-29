package com.george.tasker.desktop.view.notes

import com.george.tasker.desktop.controller.NotesController
import com.george.tasker.desktop.model.Note
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import tornadofx.*

class CenterNotesView : View() {
    private val notesController: NotesController by inject()
    private var titleTextField: TextField by singleAssign()
    private var descriptionTextArea: TextArea by singleAssign()
    private var saveButton: Button by singleAssign()

    override val root = splitpane {
        orientation = Orientation.HORIZONTAL
        setDividerPosition(0, 0.3)

        notesController.openConnection()
        notesController.getNotes()

        listview(notesController.listOfNotes) {
            isEditable = true
            cellFormat {
                var title = it.title

                if(title.isEmpty()){
                    title = "No title"
                }

                graphic = cache {
                    vbox {
                        label(title) {
                            style {
                                fontSize = 20.px
                            }
                        }
                        label(it.description)

                    }
                }

            }
        }.selectionModel
            .selectedItemProperty()
            .addListener { _, _, newValue ->
                if (newValue != null) {
                    val note = Note(newValue.id, newValue.title, newValue.description)

                    titleTextField.text = note.title
                    descriptionTextArea.text = note.description

                    saveButton.text = "Обновить"
                    saveButton.action {
                        val actualNote = Note(newValue.id, titleTextField.text, descriptionTextArea.text)
                        notesController.editNote(actualNote)
                        titleTextField.text = ""
                        descriptionTextArea.text = ""
                    }
                }
            }

        anchorpane {
            titleTextField = textfield {
                anchorpaneConstraints {
                    topAnchor = 5.0
                    leftAnchor = 10.0
                    rightAnchor = 10.0
                }
            }
            descriptionTextArea = textarea {
                anchorpaneConstraints {
                    topAnchor = 35.0
                    leftAnchor = 10.0
                    rightAnchor = 10.0
                    bottomAnchor = 45.0
                }
            }
            buttonbar {
                anchorpaneConstraints {
                    bottomAnchor = 10.0
                    rightAnchor = 10.0
                }

                saveButton = button("Сохранить") {
                    action {
                        if (descriptionTextArea.text.equals("")) {
                            println("description empty")
                            return@action
                        }

                        val note = Note(0, titleTextField.text, descriptionTextArea.text)
                        notesController.addNote(note)
                        titleTextField.text = ""
                        descriptionTextArea.text = ""
                    }
                }


            }
        }
    }

}
