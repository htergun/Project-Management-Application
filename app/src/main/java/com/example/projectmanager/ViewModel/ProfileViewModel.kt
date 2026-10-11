package com.example.projectmanager.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmanager.Domain.ArchiveDomain
import com.example.projectmanager.Domain.TeamDomain
import com.example.projectmanager.Repository.IProfileRepository
import com.example.projectmanager.Repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: IProfileRepository = ProfileRepository()): ViewModel() {

    private val _teamState = MutableStateFlow<List<TeamDomain>>(emptyList())
    val teamState: StateFlow<List<TeamDomain>> = _teamState.asStateFlow()

    private val _archiveState = MutableStateFlow<List<ArchiveDomain>>(emptyList())
    val archiveState: StateFlow<List<ArchiveDomain>> = _archiveState.asStateFlow()

    init {
        loadProfileData()
    }

    fun loadProfileData() {
        viewModelScope.launch {
            _teamState.value = repository.getTeamProjects()
            _archiveState.value = repository.getArchives()
        }
    }

    fun loadDataMyteam(): List<TeamDomain> = _teamState.value.ifEmpty {
        listOf(
            TeamDomain("Yerli Plaka", "Completion", "ongoing1", "v2.1"),
            TeamDomain("EzanPro", "Completed", "ongoing2", "v1.5"),
            TeamDomain("NoteBook App", "Completion", "ongoing3", "v1.2"),
            TeamDomain("68-AKSARAY", "Completed", "ongoing4", "v1.0")
        )
    }

    fun loadDataArchive(): List<ArchiveDomain> = _archiveState.value.ifEmpty {
        listOf(
            ArchiveDomain("REST API", ".NET & C#", "folder"),
            ArchiveDomain("Mobile Client", "Kotlin", "folder"),
            ArchiveDomain("Database", "Microsoft SQL Server", "folder")
        )
    }
}
