package com.george.tasker.desktop.view.notes

import com.george.tasker.desktop.controller.NotesController
import com.george.tasker.desktop.utils.Utils
import javafx.geometry.Orientation
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import tornadofx.*

class CenterNotesView : View() {
    private val notesController: NotesController by inject()
    private var titleTextField: TextField by singleAssign()
    private var descriptionTextArea: TextArea by singleAssign()
    private val utils = Utils()

    override val root = splitpane {
        orientation = Orientation.HORIZONTAL
        setDividerPosition(0, 0.3)

        stackpane {
            listview(notesController.listOfNotes) {
                isEditable = true
                cellFormat {
                    // todo: Изменить вид списка
                    graphic = cache {
                        form {
                            fieldset {
                                field { label(it.title) { style { fontSize = 15.px } } }
                            }

                            fieldset {
                                field { label(it.dateCreate) }
                            }
                        }

                    }

                }
            }.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
                titleTextField.text = newValue.title
                descriptionTextArea.text = newValue.description
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

            button("Сохранить") {
               anchorpaneConstraints {
                   bottomAnchor = 10.0
                   rightAnchor = 10.0
               }
            }.action {
                if (descriptionTextArea.text.equals("")) {
                    println("description empty")
                } else {
                    notesController.addNote(titleTextField.text, descriptionTextArea.text, utils.getDate()!!)
                    titleTextField.text = ""
                    descriptionTextArea.text = ""
                }
            }

        }
    }
}
