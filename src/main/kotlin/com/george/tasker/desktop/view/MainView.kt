package com.george.tasker.desktop.view
import tornadofx.*

class MainView: View() {
    override val root = borderpane {
        setPrefSize(600.0, 400.0)
        top<TopView>()
        bottom<BottomView>()
    }

}

class TopView: View() {
    override val root = label("TEST")
}


class BottomView: View() {
    override val root = label("TEST")
}