package com.example.projectmanager.Repository

import com.example.projectmanager.Domain.OngoingDomain

interface IMainRepository {
    suspend fun getOngoingProjects(): List<OngoingDomain>
}

class MainRepository : IMainRepository {
    override suspend fun getOngoingProjects(): List<OngoingDomain> = listOf(
        OngoingDomain("Yerli Plaka", "August 24, 2005", 50, "ongoing1"),
        OngoingDomain("EzanPro", "February 19, 2023", 60, "ongoing2"),
        OngoingDomain("NoteBook App", "March 10, 2026", 25, "ongoing3"),
        OngoingDomain("68-AKSARAY", "April 27, 2019", 80, "ongoing4")
    )
}
