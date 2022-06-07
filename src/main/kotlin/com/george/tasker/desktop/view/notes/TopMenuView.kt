package com.george.tasker.desktop.view.notes

import tornadofx.*

class TopMenuView: View() {
    override val root = menubar {
        menu("Файл") {

            menu("Создать") {
                item("Заметка").action {
                    println("Создана новая заметка")
                }
                item("Задача").action {
                    println("Создана новая задача")
                }
                item("Пароль").action {
                    println("Создан новый пароль")
                }
            }

            item("Настройки", "Shortcut+S").action {
                println("Открываются настройки")
            }

            item("Выйти", "Shortcut+Q").action {
                println("Закрытие приложения...")
            }
        }

        menu("Генератор") {
            item("Открыть генератор").action {
                println("Открывается генератор")
            }
            item("Создать пароль").action {
                println("Пароль создан")
            }
        }

    }
}