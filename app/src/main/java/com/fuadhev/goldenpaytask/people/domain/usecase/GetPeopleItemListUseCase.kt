package com.fuadhev.goldenpaytask.people.domain.usecase

import com.fuadhev.goldenpaytask.people.ui.model.GenderTypes
import com.fuadhev.goldenpaytask.people.ui.model.PeopleListItem
import com.fuadhev.goldenpaytask.people.ui.model.PeopleUiModel
import javax.inject.Inject

class GetPeopleItemListUseCase {


    operator fun invoke (peopleList:List<PeopleUiModel>):List<PeopleListItem> {

        val groupedItems = mutableListOf<PeopleListItem>()

        val maleItems = peopleList.filter { it.gender == GenderTypes.MALE.type }
        if (maleItems.isNotEmpty()) {
            groupedItems.add(PeopleListItem.Header(GenderTypes.MALE.type))
            groupedItems.addAll(maleItems.map { PeopleListItem.Person(it) })
        }


        val femaleItems = peopleList.filter { it.gender == GenderTypes.FEMALE.type }
        if (femaleItems.isNotEmpty()) {
            groupedItems.add(PeopleListItem.Header(GenderTypes.FEMALE.type))
            groupedItems.addAll(femaleItems.map { PeopleListItem.Person(it) })
        }


        val naItems = peopleList.filter { it.gender == GenderTypes.NA.type }
        if (naItems.isNotEmpty()) {
            groupedItems.add(PeopleListItem.Header(GenderTypes.NA.type))
            groupedItems.addAll(naItems.map { PeopleListItem.Person(it) })
        }

        return groupedItems
    }
}