package com.example.petapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer

class MainFragment : BaseFragment<MainFragmentViewModel>() {
    override val viewModelClass: Class<MainFragmentViewModel> =MainFragmentViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        val firstText = view.findViewById<TextView>(R.id.first_text)

        val firstTextObserver = Observer<String> {
            firstText.text = it
        }

        viewModel.currentText.observe(viewLifecycleOwner, firstTextObserver)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            viewModel.changeText()
        }
    }
}