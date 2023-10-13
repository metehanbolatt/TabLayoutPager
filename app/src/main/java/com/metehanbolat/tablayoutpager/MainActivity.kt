package com.metehanbolat.tablayoutpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.metehanbolat.tablayoutpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> SettingsFragment()
                    2 -> ProfileFragment()
                    else -> error("")
                }
            }
        }

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Home"
                1 -> tab.text = "Settings"
                2 -> tab.text = "Profile"
            }
        }.attach()

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                println("selected ${tab?.text}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("unselected ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("reselected ${tab?.text}")
            }
        })
    }
}