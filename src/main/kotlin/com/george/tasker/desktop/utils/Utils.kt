package com.george.tasker.desktop.utils

import java.text.SimpleDateFormat
import java.util.*

class Utils {

     fun getDate(): String? {
        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm")
        return sdf.format(Date())
    }
}