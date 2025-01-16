package com.fuadhev.goldenpaytask.people.data.mapper

import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel
import com.fuadhev.goldenpaytask.people.ui.model.PeopleUiModel

fun PeopleDomainModel.toUiModel(): PeopleUiModel {
    return PeopleUiModel(
        name = this.name,
        eyeColor = this.eyeColor,
        gender = this.gender
    )
}
fun List<PeopleDomainModel>.toUiModelList() : List<PeopleUiModel> {
    return this.map { it.toUiModel() }
}