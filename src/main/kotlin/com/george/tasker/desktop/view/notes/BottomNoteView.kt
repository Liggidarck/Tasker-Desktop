package com.george.tasker.desktop.view.notes

import tornadofx.*

class BottomNoteView : View() {
    override var root = hbox {
        button("Корзина") {
            hboxConstraints {
                marginTop = 10.0
                marginBottom = 10.0
                marginLeft = 10.0
            }
        }.action {
            println("Открывается корзина")
        }
        button("Обновить") {
            hboxConstraints {
                marginTop = 10.0
                marginBottom = 10.0
                marginLeft = 10.0
            }

        }.action {
            println("Обновление данных с сервера...")
        }
    }

}