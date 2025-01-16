package com.fuadhev.goldenpaytask.people.data.repository

import com.fuadhev.goldenpaytask.common.DataException
import com.fuadhev.goldenpaytask.common.GetRepository
import com.fuadhev.goldenpaytask.people.data.model.remote.PeopleRemoteModel
import com.fuadhev.goldenpaytask.people.domain.model.PeopleDomainModel
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery

interface PeopleRepository :GetRepository<PeopleQuery,@JvmSuppressWildcards List<PeopleDomainModel>,DataException.DataNotFoundException>