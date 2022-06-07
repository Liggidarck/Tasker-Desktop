package com.george.tasker.desktop.app
import com.george.tasker.desktop.view.notes.MainNotesView
import tornadofx.*

class TakerMain: App(MainNotesView::class) {
    fun main(args: Array<String>) {
        launch<TakerMain>(args)
    }
}