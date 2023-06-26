package com.example.petapp.firstScreen

import android.content.Context
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
import com.example.petapp.ShowFragment
import com.example.petapp.databinding.FragmentMainBinding
import com.example.petapp.secondScreen.SecondFragment

class MainFragment : BaseFragment<MainFragmentViewModel, FragmentMainBinding>() {
    override val viewModelClass: Class<MainFragmentViewModel> = MainFragmentViewModel::class.java
    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

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
        view.findViewById<Button>(R.id.nav_button).setOnClickListener {
            showFragment.show(SecondFragment.getInstance())
        }

    }

    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}