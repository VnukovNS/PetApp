package com.example.petapp.secondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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

        // todo сделать стейты с загрузкой, (loading, error)
        binding.progressBar.visibility = View.GONE

        val titleObserver = Observer<String> {
            binding.newsTitle.text = it
        }
        viewModel.titleText.observe(viewLifecycleOwner, titleObserver)
        binding.button.setOnClickListener {
            viewModel.getAutomobileNews()
        }
        val descriptionObserver = Observer<String> {
            binding.newsDescription.text = it
        }
        viewModel.descriptionText.observe(viewLifecycleOwner, descriptionObserver)
    }
}
