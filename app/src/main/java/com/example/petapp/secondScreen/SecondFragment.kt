package com.example.petapp.secondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petapp.core.BaseFragment
import com.example.petapp.databinding.SecondFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : BaseFragment<SecondFragmentBinding>() {
    private val viewModel by viewModel<SecondFragmentViewModel>()

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SecondFragmentBinding = SecondFragmentBinding.inflate(inflater, container, false)

    companion object {
        fun getInstance(): SecondFragment = SecondFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsAdapter.Base(
            object : NewsListener {
                override fun openFullNews(item: NewsPreviewUi) {
                    // сделать переход на следующий фрагмент
                }
            }

        )
        // todo сделать стейты с загрузкой, (loading, error)
        viewModel.observe(this) {
            it.map(adapter, requireContext())
        }
        with(binding) {
            recycler.adapter = adapter
            progressBar.visibility = View.GONE
        }

        binding.button.setOnClickListener {
            viewModel.getNews()
        }
    }
}
