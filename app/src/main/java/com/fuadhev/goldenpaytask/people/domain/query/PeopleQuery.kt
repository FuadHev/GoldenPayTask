package com.fuadhev.goldenpaytask.people.domain.query

sealed interface PeopleQuery {

    data object GetPeopleQuery:PeopleQuery
}