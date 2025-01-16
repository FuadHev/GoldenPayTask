package com.fuadhev.goldenpaytask.people.data.mapper

import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel
import com.fuadhev.goldenpaytask.people.ui.model.PeopleUiModel

fun PeopleRemoteModel.toDomainModel(): PeopleDomainModel {
    return PeopleDomainModel(
        name = this.name,
        eyeColor = this.eyeColor,
        gender = this.gender
    )
}

fun List<PeopleRemoteModel>.toDomainModelList(): List<PeopleDomainModel> {
    return this.map { it.toDomainModel() }
}

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