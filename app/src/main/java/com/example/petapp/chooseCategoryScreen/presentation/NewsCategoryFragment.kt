package com.example.petapp.chooseCategoryScreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petapp.core.presentation.BaseFragment
import com.example.petapp.databinding.FragmentMainBinding
import com.example.petapp.newsListScreen.presentation.NewsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsCategoryFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel by viewModel<NewsCategoryViewModel.Base>()

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.automobileButton.setOnClickListener {
            viewModel.chooseCategory("automobile")
            viewModel.navigate(NewsScreen)
        }
        binding.businessButton.setOnClickListener {
            viewModel.chooseCategory("business")
            viewModel.navigate(NewsScreen)
        }

        viewModel.observe(this){
            it.blockButtons(binding.automobileButton, requireContext())
            it.blockButtons(binding.businessButton, requireContext())
            it.showProgressBar(binding.progressBar, requireContext())
        }

        viewModel.init()
    }
}
