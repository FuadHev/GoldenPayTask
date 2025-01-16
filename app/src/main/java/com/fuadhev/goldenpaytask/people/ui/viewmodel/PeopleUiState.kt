package com.fuadhev.goldenpaytask.people.ui.viewmodel

import com.fuadhev.goldenpaytask.people.ui.model.PeopleListItem

data class PeopleUiState(
    val peopleItemList: List<PeopleListItem> = emptyList(),
    val isLoading: Boolean = false,
    var toastMessage: ToastMessage? = null
)


data class ToastMessage(
    val messageId: Int,
)