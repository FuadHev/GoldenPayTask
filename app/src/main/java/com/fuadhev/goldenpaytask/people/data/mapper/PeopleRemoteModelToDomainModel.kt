package com.fuadhev.goldenpaytask.people.data.mapper

import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel

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
