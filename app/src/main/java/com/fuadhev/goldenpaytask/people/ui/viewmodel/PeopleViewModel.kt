package com.fuadhev.goldenpaytask.people.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuadhev.goldenpaytask.R
import com.fuadhev.goldenpaytask.people.data.mapper.toUiModelList
import com.fuadhev.goldenpaytask.people.domain.query.PeopleQuery
import com.fuadhev.goldenpaytask.people.domain.usecase.GetPeopleItemListUseCase
import com.fuadhev.goldenpaytask.people.domain.usecase.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase,
    private val getPeopleItemListUseCase: GetPeopleItemListUseCase) :
    ViewModel() {


    private val _state: MutableStateFlow<PeopleUiState> =
        MutableStateFlow(PeopleUiState())

    val state: StateFlow<PeopleUiState> = _state


    private fun updateState(update: PeopleUiState.() -> PeopleUiState) {
        _state.value = _state.value.update()
    }

    init {
        getPeopleDataFromRemote()
    }


    private fun getPeopleDataFromRemote() {
        viewModelScope.launch {
            _state.value = PeopleUiState(isLoading = true)

            getPeopleUseCase.invoke(PeopleQuery.GetPeopleQuery).onRight { peopleList->

                _state.value = PeopleUiState(
                    isLoading = false,
                    peopleItemList = getPeopleItemListUseCase(peopleList = peopleList.toUiModelList())
                )

            }.onLeft {
                updateState {
                    copy(isLoading = false, toastMessage = ToastMessage(messageId = R.string.err_msg_data_not_found))
                }
            }


        }
    }

}