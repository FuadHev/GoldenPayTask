package com.fuadhev.goldenpaytask.people.ui.model

sealed class PeopleListItem {
    data class Header(val gender: String) : PeopleListItem()
    data class Person(val person : PeopleUiModel):PeopleListItem()
}