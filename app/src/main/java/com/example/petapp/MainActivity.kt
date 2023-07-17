package com.example.petapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petapp.databinding.ActivityMainBinding
import com.example.petapp.chooseCategoryScreen.presentation.FirstScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel.Base>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        viewModel.observe(this) {
            it.navigate(supportFragmentManager, binding.container.id)
        }

        viewModel.navigate(FirstScreen)
    }
}
