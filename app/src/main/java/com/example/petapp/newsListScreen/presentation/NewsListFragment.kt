package com.example.petapp.newsListScreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.petapp.core.presentation.BaseFragment
import com.example.petapp.databinding.SecondFragmentBinding
import com.example.petapp.detailnewsScreen.presentation.DetailNewsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : BaseFragment<SecondFragmentBinding>() {
    private val viewModel by viewModel<NewsListViewModel.Base>()

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SecondFragmentBinding = SecondFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewsAdapter.Base(
            object : NewsListener {
                override fun openFullNews(item: NewsPreviewUi, position: Int) {
                    viewModel.saveNewsDataId(position)
                    viewModel.navigate(DetailNewsScreen)
                }
            }

        )
        // todo сделать стейты с загрузкой, (loading, error)
        viewModel.observe(this) {
            it.map(adapter, requireContext())
            it.showProgressBar(binding.progressBar, requireContext())

        }
        with(binding) {
            recycler.adapter = adapter
        }

        viewModel.observeError(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
        viewModel.init(savedInstanceState == null)
    }
}
