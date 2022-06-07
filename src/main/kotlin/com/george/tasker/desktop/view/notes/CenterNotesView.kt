package com.george.tasker.desktop.view.notes

import com.george.tasker.desktop.controller.NotesController
import javafx.geometry.Orientation
import tornadofx.*

class CenterNotesView : View() {
    private val notesController: NotesController by inject()

    override val root = splitpane {
        orientation = Orientation.HORIZONTAL
        setDividerPosition(0, 0.3)
        stackpane {
            listview(notesController.listOfNotes) {
                isEditable = true
                cellFormat {
                    graphic = cache {
                        form {
                            fieldset {
                                field {
                                    label(it.title) {
                                        style {
                                            fontSize = 15.px
                                        }
                                    }
                                }
                            }

                            fieldset {
                                field {
                                    label(it.dateCreate)
                                }
                            }

                        }

                    }
                }

            }
        }
        vbox {
            textarea()
            button("Сохранить").action {
                notesController.addNote("TEST", "test", "test")
            }

        }
    }

}
