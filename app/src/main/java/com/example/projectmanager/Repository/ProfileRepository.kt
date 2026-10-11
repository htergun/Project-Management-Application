package com.example.projectmanager.Repository

import com.example.projectmanager.Domain.ArchiveDomain
import com.example.projectmanager.Domain.TeamDomain

interface IProfileRepository {
    suspend fun getTeamProjects(): List<TeamDomain>
    suspend fun getArchives(): List<ArchiveDomain>
}

class ProfileRepository : IProfileRepository {
    override suspend fun getTeamProjects(): List<TeamDomain> = listOf(
        TeamDomain("Yerli Plaka", "Completion", "ongoing1", "v2.1"),
        TeamDomain("EzanPro", "Completed", "ongoing2", "v1.5"),
        TeamDomain("NoteBook App", "Completion", "ongoing3", "v1.2"),
        TeamDomain("68-AKSARAY", "Completed", "ongoing4", "v1.0")
    )

    override suspend fun getArchives(): List<ArchiveDomain> = listOf(
        ArchiveDomain("REST API", ".NET & C#", "folder"),
        ArchiveDomain("Mobile Client", "Kotlin", "folder"),
        ArchiveDomain("Database", "Microsoft SQL Server", "folder")
    )
}
