package com.george.tasker.desktop.view.notes

import tornadofx.View
import tornadofx.borderpane

class MainNotesView: View("Заметки") {
    override val root = borderpane {
        setPrefSize(1280.0, 720.0)
        top<TopMenuView>()
        center<CenterNotesView>()
        bottom<BottomNoteView>()
    }
}