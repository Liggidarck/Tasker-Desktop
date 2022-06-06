package com.george.tasker.desktop.app
import com.george.tasker.desktop.view.MainView
import tornadofx.*

class TakerMain: App(MainView::class) {
    fun main(args: Array<String>) {
        launch<TakerMain>(args)
    }
}