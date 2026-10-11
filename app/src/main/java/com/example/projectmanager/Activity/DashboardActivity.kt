package com.example.projectmanager.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectmanager.Adapter.OngoingAdapter
import com.example.projectmanager.ViewModel.MainViewModel
import com.example.projectmanager.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val ongoingAdapter = OngoingAdapter(mainViewModel.loadData())

            viewOngoing.apply {
                adapter = ongoingAdapter
                layoutManager = GridLayoutManager(this@DashboardActivity, 2)
            }

            lifecycleScope.launch {
                mainViewModel.projectsState.collectLatest { projects ->
                    if (projects.isNotEmpty()) {
                        viewOngoing.adapter = OngoingAdapter(projects)
                    }
                }
            }

            profileBtn.setOnClickListener {
                startActivity(Intent(this@DashboardActivity, ProfileActivity::class.java))
            }

            headerProfileImg.setOnClickListener {
                startActivity(Intent(this@DashboardActivity, ProfileActivity::class.java))
            }

            notificationBtn.setOnClickListener {
                Toast.makeText(this@DashboardActivity, "No new notifications", Toast.LENGTH_SHORT).show()
            }

            addProjectBtn.setOnClickListener {
                Toast.makeText(this@DashboardActivity, "Add new project clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
