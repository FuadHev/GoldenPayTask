package com.fuadhev.goldenpaytask.people.ui.model

import com.fuadhev.goldenpaytask.R

enum class GenderTypes(val type: String) {
    MALE("male"), FEMALE("female"), NA("n/a");


    companion object {

        fun getStringResourceByType(type: String): Int {
            return when (type) {
                MALE.type -> R.string.label_gender_male
                FEMALE.type -> R.string.label_gender_female
                NA.type -> R.string.label_gender_na
                else->R.string.label_gender_na
            }

        }

    }
}