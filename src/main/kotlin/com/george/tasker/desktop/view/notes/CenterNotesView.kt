package com.george.tasker.desktop.view.notes

import com.george.tasker.desktop.controller.NotesController
import com.george.tasker.desktop.model.Note
import com.george.tasker.desktop.utils.Utils
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
    private val utils = Utils()

    override val root = splitpane {
        orientation = Orientation.HORIZONTAL
        setDividerPosition(0, 0.3)

        listview(notesController.listOfNotes) {
            isEditable = true
            cellFormat {
                // todo: Изменить вид списка
                graphic = cache {
                    vbox {
                        form {
                            fieldset {
                                field { label(it.title) { style { fontSize = 15.px } } }
                            }

                            fieldset {
                                field { label(it.dateCreate) }
                            }
                        }
                        button("Удалить").action {
                            titleTextField.text = ""
                            descriptionTextArea.text = ""

                            notesController.deleteNote(it)
                        }
                    }
                }

            }
        }.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if (newValue != null) {
                val note = Note(newValue.title, newValue.description, newValue.dateCreate)

                titleTextField.text = note.title
                descriptionTextArea.text = note.description

                saveButton.action {
                    val actualNote = Note(titleTextField.text, descriptionTextArea.text, utils.getDate()!!)
                    notesController.editNote(note, actualNote)

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
                button("Копировать")

                saveButton = button("Сохранить") {
                    action {
                        if (descriptionTextArea.text.equals("")) {
                            println("description empty")
                        } else {
                            val note = Note(titleTextField.text, descriptionTextArea.text, utils.getDate()!!)
                            notesController.addNote(note)

                            titleTextField.text = ""
                            descriptionTextArea.text = ""
                        }
                    }
                }


            }
        }
    }

}
