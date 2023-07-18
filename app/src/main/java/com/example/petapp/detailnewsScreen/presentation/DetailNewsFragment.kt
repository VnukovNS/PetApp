package com.example.petapp.detailnewsScreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petapp.core.presentation.BaseFragment
import com.example.petapp.databinding.DetailNewsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailNewsFragment : BaseFragment<DetailNewsFragmentBinding>() {

    private val viewModel by viewModel<DetailNewsViewModel.Base>()

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailNewsFragmentBinding = DetailNewsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(this) {
            with(binding) {
                it.map(
                    newsTitle,
                    newsImage,
                    newsContent,
                    newsAuthor,
                    newsDate,
                    newsReadMore,
                    requireContext()
                )
                it.showProgressBar(progressBar)
            }
        }

        viewModel.init()
    }
}