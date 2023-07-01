package com.example.petapp.secondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.petapp.core.BaseFragment
import com.example.petapp.databinding.SecondFragmentBinding

class SecondFragment : BaseFragment<SecondFragmentViewModel, SecondFragmentBinding>() {
    override val viewModelClass: Class<SecondFragmentViewModel> =
        SecondFragmentViewModel::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SecondFragmentBinding = SecondFragmentBinding.inflate(inflater, container, false)

    companion object {
        fun getInstance(): SecondFragment = SecondFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.progressBar.visibility = View.GONE

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

//        val titleObserver = Observer<String> {
//            binding.newsItem.setTitle(it)
//        }
//        val descriptionObserver = Observer<String> {
//            binding.newsItem.setContent(it)
//        }
//        viewModel.titleText.observe(viewLifecycleOwner, titleObserver)
//        viewModel.descriptionText.observe(viewLifecycleOwner, descriptionObserver)

        binding.button.setOnClickListener {
            viewModel.getNews()
        }
    }
}
