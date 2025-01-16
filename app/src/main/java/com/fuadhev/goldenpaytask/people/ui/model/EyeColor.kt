package com.fuadhev.goldenpaytask.people.ui.model

import android.graphics.Color

enum class EyeColor(val color: Int) {
    BLUE(Color.BLUE),
    BROWN(Color.rgb(139, 69, 19)),
    YELLOW(Color.YELLOW),
    RED(Color.RED),
    GREEN(Color.GREEN),
    GRAY(Color.GRAY),
    WHITE(Color.WHITE),
    BLACK(Color.BLACK),
    UNKNOWN(Color.LTGRAY);

    companion object {
        fun fromStringColor(value: String): EyeColor {
            return values().find { it.name.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}