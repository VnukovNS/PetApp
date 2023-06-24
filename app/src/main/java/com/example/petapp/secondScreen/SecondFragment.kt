package com.example.petapp.secondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.petapp.core.BaseFragment
import com.example.petapp.R

class SecondFragment : BaseFragment<SecondFragmentViewModel>() {
    override val viewModelClass: Class<SecondFragmentViewModel> =
        SecondFragmentViewModel::class.java

    companion object {
        fun getInstance() : SecondFragment = SecondFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
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