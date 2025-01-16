package com.fuadhev.goldenpaytask.people.data.datasource

import com.fuadhev.goldenpaytask.common.DataException
import com.fuadhev.goldenpaytask.common.GetDatasource
import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery

interface PeopleRemoteDataSource : GetDatasource<PeopleQuery,List<PeopleRemoteModel>,DataException.DataNotFoundException> {
}