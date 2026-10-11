package com.example.projectmanager.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmanager.Domain.OngoingDomain
import com.example.projectmanager.Repository.IMainRepository
import com.example.projectmanager.Repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IMainRepository = MainRepository()): ViewModel() {

    private val _projectsState = MutableStateFlow<List<OngoingDomain>>(emptyList())
    val projectsState: StateFlow<List<OngoingDomain>> = _projectsState.asStateFlow()

    init {
        loadData()
    }

    fun loadData(): List<OngoingDomain> {
        viewModelScope.launch {
            _projectsState.value = repository.getOngoingProjects()
        }
        return _projectsState.value
    }
}
