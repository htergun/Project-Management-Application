package com.example.projectmanager.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmanager.Adapter.ArchiveAdapter
import com.example.projectmanager.Adapter.MyTeamAdapter
import com.example.projectmanager.ViewModel.ProfileViewModel
import com.example.projectmanager.databinding.ActivityProfileBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            viewTeam.apply {
                adapter = MyTeamAdapter(profileViewModel.loadDataMyteam())
                layoutManager = LinearLayoutManager(
                    this@ProfileActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            viewArchive.apply {
                adapter = ArchiveAdapter(profileViewModel.loadDataArchive())
                layoutManager = LinearLayoutManager(
                    this@ProfileActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }

            lifecycleScope.launch {
                profileViewModel.teamState.collectLatest { teamItems ->
                    if (teamItems.isNotEmpty()) {
                        viewTeam.adapter = MyTeamAdapter(teamItems)
                    }
                }
            }

            lifecycleScope.launch {
                profileViewModel.archiveState.collectLatest { archiveItems ->
                    if (archiveItems.isNotEmpty()) {
                        viewArchive.adapter = ArchiveAdapter(archiveItems)
                    }
                }
            }

            homeBtn.setOnClickListener {
                startActivity(Intent(this@ProfileActivity, DashboardActivity::class.java))
                finish()
            }

            addProjectBtn.setOnClickListener {
                Toast.makeText(this@ProfileActivity, "Add new project clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
